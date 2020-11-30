#include "DebugState.hpp"

DebugState::DebugState() { }
DebugState::DebugState(Vec2Int windowSize, Vec2Float mousePosWindow, Vec2Float mousePosWorld, std::vector<std::string> pressedKeys, Camera camera, int playerIndex) : windowSize(windowSize), mousePosWindow(mousePosWindow), mousePosWorld(mousePosWorld), pressedKeys(pressedKeys), camera(camera), playerIndex(playerIndex) { }
DebugState DebugState::readFrom(InputStream& stream) {
    DebugState result;
    result.windowSize = Vec2Int::readFrom(stream);
    result.mousePosWindow = Vec2Float::readFrom(stream);
    result.mousePosWorld = Vec2Float::readFrom(stream);
    result.pressedKeys = std::vector<std::string>(stream.readInt());
    for (size_t i = 0; i < result.pressedKeys.size(); i++) {
        result.pressedKeys[i] = stream.readString();
    }
    result.camera = Camera::readFrom(stream);
    result.playerIndex = stream.readInt();
    return result;
}
void DebugState::writeTo(OutputStream& stream) const {
    windowSize.writeTo(stream);
    mousePosWindow.writeTo(stream);
    mousePosWorld.writeTo(stream);
    stream.write((int)(pressedKeys.size()));
    for (const std::string& pressedKeysElement : pressedKeys) {
        stream.write(pressedKeysElement);
    }
    camera.writeTo(stream);
    stream.write(playerIndex);
}
