#include "DebugInterface.hpp"
#include "MyStrategy.hpp"
#include "TcpStream.hpp"
#include "model/Model.hpp"
#include <memory>
#include <string>

class Runner {
public:
    Runner(const std::string& host, int port, const std::string& token)
    {
        std::shared_ptr<TcpStream> tcpStream(new TcpStream(host, port));
        inputStream = getInputStream(tcpStream);
        outputStream = getOutputStream(tcpStream);
        outputStream->write(token);
        outputStream->flush();
    }
    void run()
    {
        DebugInterface debugInterface(inputStream, outputStream);
        MyStrategy myStrategy;
        while (true) {
            auto message = ServerMessage::readFrom(*inputStream);
            if (auto getActionMessage = std::dynamic_pointer_cast<ServerMessage::GetAction>(message)) {
                ClientMessage::ActionMessage(myStrategy.getAction(getActionMessage->playerView, getActionMessage->debugAvailable ? &debugInterface : nullptr)).writeTo(*outputStream);
                outputStream->flush();
            } else if (auto finishMessage = std::dynamic_pointer_cast<ServerMessage::Finish>(message)) {
                break;
            } else if (auto debugUpdateMessage = std::dynamic_pointer_cast<ServerMessage::DebugUpdate>(message)) {
                myStrategy.debugUpdate(debugUpdateMessage->playerView, debugInterface);
                ClientMessage::DebugUpdateDone().writeTo(*outputStream);
                outputStream->flush();
            }
        }
    }

private:
    std::shared_ptr<InputStream> inputStream;
    std::shared_ptr<OutputStream> outputStream;
};

int main(int argc, char* argv[])
{
    std::string host = argc < 2 ? "127.0.0.1" : argv[1];
    int port = argc < 3 ? 31001 : atoi(argv[2]);
    std::string token = argc < 4 ? "0000000000000000" : argv[3];
    Runner(host, port, token).run();
    return 0;
}