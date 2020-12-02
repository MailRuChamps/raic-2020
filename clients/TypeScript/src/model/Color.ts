import {Stream} from './Stream';

export class Color {
    constructor(
        private r: number,
        private g: number,
        private b: number,
        private a: number
    ) {}

    static async readFrom(stream: Stream) {
        const r = await stream.readFloat();
        const g = await stream.readFloat();
        const b = await stream.readFloat();
        const a = await stream.readFloat();

        return new Color(r, g, b, a);
    }
    async writeTo(stream: Stream) {
        await stream.writeFloat(this.r);
        await stream.writeFloat(this.g);
        await stream.writeFloat(this.b);
        await stream.writeFloat(this.a);
    }
}
