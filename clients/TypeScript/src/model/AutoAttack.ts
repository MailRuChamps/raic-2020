import {Stream} from './Stream';
import {EntityType} from './EntityType';

export class AutoAttack {
    constructor(private pathfindRange: number, private validTargets: EntityType[]) {
    }
    static async readFrom(stream: Stream) {
        const pathfindRange = await stream.readInt();
        const validTargets = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            const validTargetsElement = await stream.readInt();
            validTargets.push(validTargetsElement);
        }

        return new AutoAttack(pathfindRange, validTargets);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(this.pathfindRange);
        await stream.writeInt(this.validTargets.length);
        for (let validTargetsElement of this.validTargets) {
            await stream.writeInt(validTargetsElement);
        }
    }
}
