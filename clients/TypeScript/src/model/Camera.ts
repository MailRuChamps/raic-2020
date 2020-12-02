import {Vec2Float} from './Vec2Float';
import {Stream} from './Stream';

export class Camera {
    constructor(
        private center: Vec2Float,
        private rotation: number,
        private attack: number,
        private distance: number,
        private perspective: boolean
    ) {}

    static async readFrom(stream: Stream) {
        const center = await Vec2Float.readFrom(stream);
        const rotation = await stream.readFloat();
        const attack = await stream.readFloat();
        const distance = await stream.readFloat();
        const perspective = await stream.readBool();

        return new Camera(center, rotation, attack, distance, perspective);
    }
    async writeTo(stream: Stream) {
        await this.center.writeTo(stream);
        await stream.writeFloat(this.rotation);
        await stream.writeFloat(this.attack);
        await stream.writeFloat(this.distance);
        await stream.writeBool(this.perspective);
    }
}
