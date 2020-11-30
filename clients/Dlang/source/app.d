import model;
import my_strategy;
import stream;
import debug_interface;
import std.socket;
import std.conv;
import std.exception;

class SocketStream : Stream
{
    this(Socket socket)
    {
        this.socket = socket;
    }

    override ubyte[] readBytes(size_t byteCount)
    {
        ubyte[] data = new ubyte[byteCount];
        size_t offset = 0;
        while (offset < byteCount)
        {
            auto received = socket.receive(data[offset .. data.length]);
            enforce(received > 0);
            offset += received;
        }
        return data;
    }

    override void writeBytes(const ubyte[] data)
    {
        size_t offset = 0;
        while (offset < data.length)
        {
            auto sent = socket.send(data[offset .. data.length]);
            enforce(sent > 0);
            offset += sent;
        }
    }

    override void flush()
    {
    }

private:
    Socket socket;
}

class Runner
{
    this(string host, ushort port, string token)
    {
        auto addr = getAddress(host, port)[0];
        auto socket = new Socket(addr.addressFamily, SocketType.STREAM);
        socket.setOption(SocketOptionLevel.TCP, SocketOption.TCP_NODELAY, true);
        socket.connect(addr);
        stream = new SocketStream(socket);
        stream.write(token);
        stream.flush();
    }

    void run()
    {
        auto myStrategy = new MyStrategy();
        auto debugInterface = new DebugInterface(stream);
        while (true)
        {
            ServerMessage message = ServerMessage.readFrom(stream);
            if (auto getActionMessage = cast(ServerMessage.GetAction)(message))
            {
                new ClientMessage.ActionMessage(myStrategy.getAction(getActionMessage.playerView,
                        getActionMessage.debugAvailable ? debugInterface : null)).writeTo(stream);
                stream.flush();
            }
            else if (auto finishMessage = cast(ServerMessage.Finish)(message))
            {
                break;
            }
            else if (auto debugUpdateMessage = cast(ServerMessage.DebugUpdate)(message))
            {
                myStrategy.debugUpdate(debugUpdateMessage.playerView, debugInterface);
                new ClientMessage.DebugUpdateDone().writeTo(stream);
                stream.flush();
            }
            else
            {
                throw new Error("Unexpected server message");
            }
        }
    }

private:
    Stream stream;
}

void main(string[] args)
{
    string host = args.length < 2 ? "127.0.0.1" : args[1];
    ushort port = args.length < 3 ? 31001 : to!ushort(args[2]);
    string token = args.length < 4 ? "0000000000000000" : args[3];

    new Runner(host, port, token).run();
}
