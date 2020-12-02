import {Stream} from './Stream';

export class AttackProperties {
    constructor(
        private attackRange: number,
        private damage: number,
        private collectResource: boolean,
    ) {}

    static async readFrom(stream: Stream): Promise<AttackProperties> {
        const attackRange = await stream.readInt();
        const damage = await stream.readInt();
        const collectResource = await stream.readBool();

        return new AttackProperties(attackRange, damage, collectResource);
    }

    async writeTo(stream: Stream): Promise<void> {
        await stream.writeInt(this.attackRange);
        await stream.writeInt(this.damage);
        await stream.writeBool(this.collectResource);
    }
}
