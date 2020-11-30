const Vec2Int = require('./vec2-int');
const Vec2Float = require('./vec2-float');
const Camera = require('./camera');
class DebugState {
    constructor(windowSize, mousePosWindow, mousePosWorld, pressedKeys, camera, playerIndex) {
        this.windowSize = windowSize;
        this.mousePosWindow = mousePosWindow;
        this.mousePosWorld = mousePosWorld;
        this.pressedKeys = pressedKeys;
        this.camera = camera;
        this.playerIndex = playerIndex;
    }
    static async readFrom(stream) {
        let windowSize;
        windowSize = await Vec2Int.readFrom(stream);
        let mousePosWindow;
        mousePosWindow = await Vec2Float.readFrom(stream);
        let mousePosWorld;
        mousePosWorld = await Vec2Float.readFrom(stream);
        let pressedKeys;
        pressedKeys = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let pressedKeysElement;
            pressedKeysElement = await stream.readString();
            pressedKeys.push(pressedKeysElement);
        }
        let camera;
        camera = await Camera.readFrom(stream);
        let playerIndex;
        playerIndex = await stream.readInt();
        return new DebugState(windowSize, mousePosWindow, mousePosWorld, pressedKeys, camera, playerIndex);
    }
    async writeTo(stream) {
        let windowSize = this.windowSize;
        await windowSize.writeTo(stream);
        let mousePosWindow = this.mousePosWindow;
        await mousePosWindow.writeTo(stream);
        let mousePosWorld = this.mousePosWorld;
        await mousePosWorld.writeTo(stream);
        let pressedKeys = this.pressedKeys;
        await stream.writeInt(pressedKeys.length);
        for (let pressedKeysElement of pressedKeys) {
            await stream.writeString(pressedKeysElement);
        }
        let camera = this.camera;
        await camera.writeTo(stream);
        let playerIndex = this.playerIndex;
        await stream.writeInt(playerIndex);
    }
}
module.exports = DebugState
