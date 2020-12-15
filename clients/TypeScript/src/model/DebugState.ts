import {Vec2Int} from './Vec2Int';
import {Vec2Float} from './Vec2Float';
import {Camera} from './Camera';
import {Stream} from './Stream';

export class DebugState {
    constructor(
        public windowSize: Vec2Int,
        public mousePosWindow: Vec2Float,
        public mousePosWorld: Vec2Float,
        public pressedKeys: string[],
        public camera: Camera,
        public playerIndex: number,
    ) {}

    static async readFrom(stream: Stream) {
        const windowSize = await Vec2Int.readFrom(stream);
        const mousePosWindow = await Vec2Float.readFrom(stream);
        const mousePosWorld = await Vec2Float.readFrom(stream);
        const pressedKeys = [];

        for (let i = await stream.readInt(); i > 0; i--) {
            const pressedKeysElement = await stream.readString();
            pressedKeys.push(pressedKeysElement);
        }

        const camera = await Camera.readFrom(stream);
        const playerIndex = await stream.readInt();

        return new DebugState(windowSize, mousePosWindow, mousePosWorld, pressedKeys, camera, playerIndex);
    }
    async writeTo(stream: Stream) {
        await this.windowSize.writeTo(stream);
        await this.mousePosWindow.writeTo(stream);
        await this.mousePosWorld.writeTo(stream);

        await stream.writeInt(this.pressedKeys.length);
        for (let pressedKeysElement of this.pressedKeys) {
            await stream.writeString(pressedKeysElement);
        }

        await this.camera.writeTo(stream);
        await stream.writeInt(this.playerIndex);
    }
}
