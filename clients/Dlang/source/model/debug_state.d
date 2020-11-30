import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct DebugState {
    Vec2Int windowSize;
    Vec2Float mousePosWindow;
    Vec2Float mousePosWorld;
    string[] pressedKeys;
    Camera camera;
    int playerIndex;
    this(Vec2Int windowSize, Vec2Float mousePosWindow, Vec2Float mousePosWorld, string[] pressedKeys, Camera camera, int playerIndex) {
        this.windowSize = windowSize;
        this.mousePosWindow = mousePosWindow;
        this.mousePosWorld = mousePosWorld;
        this.pressedKeys = pressedKeys;
        this.camera = camera;
        this.playerIndex = playerIndex;
    }
    static DebugState readFrom(Stream reader) {
        auto result = DebugState();
        result.windowSize = Vec2Int.readFrom(reader);
        result.mousePosWindow = Vec2Float.readFrom(reader);
        result.mousePosWorld = Vec2Float.readFrom(reader);
        result.pressedKeys = new string[reader.readInt()];
        for (int i = 0; i < result.pressedKeys.length; i++) {
            result.pressedKeys[i] = reader.readString();
        }
        result.camera = Camera.readFrom(reader);
        result.playerIndex = reader.readInt();
        return result;
    }
    void writeTo(Stream writer) const {
        windowSize.writeTo(writer);
        mousePosWindow.writeTo(writer);
        mousePosWorld.writeTo(writer);
        writer.write(cast(int)(pressedKeys.length));
        foreach (pressedKeysElement; pressedKeys) {
            writer.write(pressedKeysElement);
        }
        camera.writeTo(writer);
        writer.write(playerIndex);
    }
    string toString() const {
        return "DebugState" ~ "(" ~
            to!string(windowSize) ~
            to!string(mousePosWindow) ~
            to!string(mousePosWorld) ~
            to!string(pressedKeys) ~
            to!string(camera) ~
            to!string(playerIndex) ~
            ")";
    }
}
