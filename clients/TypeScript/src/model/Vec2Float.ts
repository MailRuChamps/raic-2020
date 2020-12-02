import {Stream} from './Stream';

export class Vec2Float {
    constructor(
        private x: number,
        private y: number,
    ) {}

    static async readFrom(stream: Stream): Promise<Vec2Float> {
        const x = await stream.readFloat();
        const y = await stream.readFloat();
        return new Vec2Float(x, y);
    }

    async writeTo(stream: Stream): Promise<void> {
        await stream.writeFloat(this.x);
        await stream.writeFloat(this.y);
    }
}
