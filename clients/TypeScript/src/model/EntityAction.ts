import {MoveAction} from './MoveAction';
import {BuildAction} from './BuildAction';
import {AttackAction} from './AttackAction';
import {RepairAction} from './RepairAction';
import {Stream} from './Stream';

export class EntityAction {
    constructor(
        private moveAction: MoveAction | null,
        private buildAction: BuildAction | null,
        private attackAction: AttackAction | null,
        private repairAction: RepairAction | null,
    ) {}

    static async readFrom(stream: Stream): Promise<EntityAction> {
        const moveAction = await stream.readBool() ? await MoveAction.readFrom(stream) : null;
        const buildAction = await stream.readBool() ? await BuildAction.readFrom(stream) : null;
        const attackAction = await stream.readBool() ? await AttackAction.readFrom(stream) : null;
        const repairAction = await stream.readBool() ? await RepairAction.readFrom(stream) : null;

        return new EntityAction(moveAction, buildAction, attackAction, repairAction);
    }

    async writeTo(stream: Stream): Promise<void> {
        if (this.moveAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.moveAction.writeTo(stream);
        }

        if (this.buildAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.buildAction.writeTo(stream);
        }

        if (this.attackAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.attackAction.writeTo(stream);
        }

        if (this.repairAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.repairAction.writeTo(stream);
        }
    }
}
