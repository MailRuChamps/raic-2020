#include "ServerMessage.hpp"
#include <stdexcept>

ServerMessage::GetAction::GetAction() { }
ServerMessage::GetAction::GetAction(PlayerView playerView, bool debugAvailable) : playerView(playerView), debugAvailable(debugAvailable) { }
ServerMessage::GetAction ServerMessage::GetAction::readFrom(InputStream& stream) {
    ServerMessage::GetAction result;
    result.playerView = PlayerView::readFrom(stream);
    result.debugAvailable = stream.readBool();
    return result;
}
void ServerMessage::GetAction::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    playerView.writeTo(stream);
    stream.write(debugAvailable);
}

ServerMessage::Finish::Finish() { }
ServerMessage::Finish ServerMessage::Finish::readFrom(InputStream& stream) {
    ServerMessage::Finish result;
    return result;
}
void ServerMessage::Finish::writeTo(OutputStream& stream) const {
    stream.write(TAG);
}

ServerMessage::DebugUpdate::DebugUpdate() { }
ServerMessage::DebugUpdate::DebugUpdate(PlayerView playerView) : playerView(playerView) { }
ServerMessage::DebugUpdate ServerMessage::DebugUpdate::readFrom(InputStream& stream) {
    ServerMessage::DebugUpdate result;
    result.playerView = PlayerView::readFrom(stream);
    return result;
}
void ServerMessage::DebugUpdate::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    playerView.writeTo(stream);
}
std::shared_ptr<ServerMessage> ServerMessage::readFrom(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return std::shared_ptr<ServerMessage::GetAction>(new ServerMessage::GetAction(ServerMessage::GetAction::readFrom(stream)));
    case 1:
        return std::shared_ptr<ServerMessage::Finish>(new ServerMessage::Finish(ServerMessage::Finish::readFrom(stream)));
    case 2:
        return std::shared_ptr<ServerMessage::DebugUpdate>(new ServerMessage::DebugUpdate(ServerMessage::DebugUpdate::readFrom(stream)));
    default:
        throw std::runtime_error("Unexpected tag value");
    }
};
