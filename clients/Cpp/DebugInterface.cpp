#include "DebugInterface.hpp"
#include "model/ClientMessage.hpp"

DebugInterface::DebugInterface(const std::shared_ptr<InputStream>& inputStream, const std::shared_ptr<OutputStream>& outputStream)
    : inputStream(inputStream)
    , outputStream(outputStream)
{
}

void DebugInterface::send(const DebugCommand& command)
{
    // TODO: Construct actual message, this is a hack :)
    outputStream->write(ClientMessage::DebugMessage::TAG);
    command.writeTo(*outputStream);
    outputStream->flush();
}

DebugState DebugInterface::getState()
{
    ClientMessage::RequestDebugState().writeTo(*outputStream);
    outputStream->flush();
    return DebugState::readFrom(*inputStream);
}