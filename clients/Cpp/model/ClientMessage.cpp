#include "ClientMessage.hpp"
#include <stdexcept>

ClientMessage::DebugMessage::DebugMessage() { }
ClientMessage::DebugMessage::DebugMessage(std::shared_ptr<DebugCommand> command) : command(command) { }
ClientMessage::DebugMessage ClientMessage::DebugMessage::readFrom(InputStream& stream) {
    ClientMessage::DebugMessage result;
    result.command = DebugCommand::readFrom(stream);
    return result;
}
void ClientMessage::DebugMessage::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    command->writeTo(stream);
}

ClientMessage::ActionMessage::ActionMessage() { }
ClientMessage::ActionMessage::ActionMessage(Action action) : action(action) { }
ClientMessage::ActionMessage ClientMessage::ActionMessage::readFrom(InputStream& stream) {
    ClientMessage::ActionMessage result;
    result.action = Action::readFrom(stream);
    return result;
}
void ClientMessage::ActionMessage::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    action.writeTo(stream);
}

ClientMessage::DebugUpdateDone::DebugUpdateDone() { }
ClientMessage::DebugUpdateDone ClientMessage::DebugUpdateDone::readFrom(InputStream& stream) {
    ClientMessage::DebugUpdateDone result;
    return result;
}
void ClientMessage::DebugUpdateDone::writeTo(OutputStream& stream) const {
    stream.write(TAG);
}

ClientMessage::RequestDebugState::RequestDebugState() { }
ClientMessage::RequestDebugState ClientMessage::RequestDebugState::readFrom(InputStream& stream) {
    ClientMessage::RequestDebugState result;
    return result;
}
void ClientMessage::RequestDebugState::writeTo(OutputStream& stream) const {
    stream.write(TAG);
}
std::shared_ptr<ClientMessage> ClientMessage::readFrom(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return std::shared_ptr<ClientMessage::DebugMessage>(new ClientMessage::DebugMessage(ClientMessage::DebugMessage::readFrom(stream)));
    case 1:
        return std::shared_ptr<ClientMessage::ActionMessage>(new ClientMessage::ActionMessage(ClientMessage::ActionMessage::readFrom(stream)));
    case 2:
        return std::shared_ptr<ClientMessage::DebugUpdateDone>(new ClientMessage::DebugUpdateDone(ClientMessage::DebugUpdateDone::readFrom(stream)));
    case 3:
        return std::shared_ptr<ClientMessage::RequestDebugState>(new ClientMessage::RequestDebugState(ClientMessage::RequestDebugState::readFrom(stream)));
    default:
        throw std::runtime_error("Unexpected tag value");
    }
};
