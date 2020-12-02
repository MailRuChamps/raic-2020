import {AutoAttack} from './AutoAttack';
import {EntityType} from './EntityType';
import {Stream} from './Stream';

export class AttackAction {
    constructor(private target: EntityType | null, private autoAttack: AutoAttack | null) {}

    static async readFrom(stream: Stream) {
        const target = await stream.readBool() ? await stream.readInt() : null;
        const autoAttack = await stream.readBool() ? await AutoAttack.readFrom(stream) : null;

        return new AttackAction(target, autoAttack);
    }
    async writeTo(stream: Stream) {
        if (this.target === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(this.target);
        }

        if (this.autoAttack === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.autoAttack.writeTo(stream);
        }
    }
}
