import {Stream} from './Stream';
import {Vec2Int} from './Vec2Int';

export class MoveAction {
    constructor(
        public target: Vec2Int,
        public findClosestPosition: boolean,
        public breakThrough: boolean,
    ) {}

    static async readFrom(stream: Stream) {
        const target = await Vec2Int.readFrom(stream);
        const findClosestPosition = await stream.readBool();
        const breakThrough = await stream.readBool();

        return new MoveAction(target, findClosestPosition, breakThrough);
    }
    async writeTo(stream: Stream) {
        await this.target.writeTo(stream);
        await stream.writeBool(this.findClosestPosition);
        await stream.writeBool(this.breakThrough);
    }
}
