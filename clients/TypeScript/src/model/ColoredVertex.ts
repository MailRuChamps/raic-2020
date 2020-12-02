import {Vec2Float} from './Vec2Float';
import {Color} from './Color';
import {Stream} from './Stream';

export class ColoredVertex {
    constructor(
        private worldPos: Vec2Float | null,
        private screenOffset: Vec2Float,
        private color: Color,
    ) {}

    static async readFrom(stream: Stream) {
        const worldPos = await stream.readBool() ? await Vec2Float.readFrom(stream) : null;
        const screenOffset = await Vec2Float.readFrom(stream);
        const color = await Color.readFrom(stream);

        return new ColoredVertex(worldPos, screenOffset, color);
    }

    async writeTo(stream: Stream) {
        if (this.worldPos === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.worldPos.writeTo(stream);
        }

        await this.screenOffset.writeTo(stream);
        await this.color.writeTo(stream);
    }
}
