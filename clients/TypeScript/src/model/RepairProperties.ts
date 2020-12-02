import {EntityType} from './EntityType';
import {Stream} from './Stream';

export class RepairProperties {
    constructor(
        public validTargets: EntityType[],
        public power: number,
    ) {}

    static async readFrom(stream: Stream) {
        const validTargets = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            validTargets.push(await stream.readInt());
        }

        const power = await stream.readInt();

        return new RepairProperties(validTargets, power);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(this.validTargets.length);

        for (let validTargetsElement of this.validTargets) {
            await stream.writeInt(validTargetsElement);
        }

        await stream.writeInt(this.power);
    }
}
