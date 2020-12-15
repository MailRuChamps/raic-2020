import {Stream} from './Stream';

export class Vec2Int {
    constructor(
        public x: number,
        public y: number,
    ) {}

    static async readFrom(stream: Stream): Promise<Vec2Int> {
        const x = await stream.readInt();
        const y = await stream.readInt();
        return new Vec2Int(x, y);
    }

    async writeTo(stream: Stream): Promise<void> {
        await stream.writeInt(this.x);
        await stream.writeInt(this.y);
    }
}
