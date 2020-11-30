const MoveAction = require('./move-action');
const BuildAction = require('./build-action');
const AttackAction = require('./attack-action');
const RepairAction = require('./repair-action');
class EntityAction {
    constructor(moveAction, buildAction, attackAction, repairAction) {
        this.moveAction = moveAction;
        this.buildAction = buildAction;
        this.attackAction = attackAction;
        this.repairAction = repairAction;
    }
    static async readFrom(stream) {
        let moveAction;
        if (await stream.readBool()) {
            moveAction = await MoveAction.readFrom(stream);
        } else {
            moveAction = null;
        }
        let buildAction;
        if (await stream.readBool()) {
            buildAction = await BuildAction.readFrom(stream);
        } else {
            buildAction = null;
        }
        let attackAction;
        if (await stream.readBool()) {
            attackAction = await AttackAction.readFrom(stream);
        } else {
            attackAction = null;
        }
        let repairAction;
        if (await stream.readBool()) {
            repairAction = await RepairAction.readFrom(stream);
        } else {
            repairAction = null;
        }
        return new EntityAction(moveAction, buildAction, attackAction, repairAction);
    }
    async writeTo(stream) {
        let moveAction = this.moveAction;
        if (moveAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await moveAction.writeTo(stream);
        }
        let buildAction = this.buildAction;
        if (buildAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await buildAction.writeTo(stream);
        }
        let attackAction = this.attackAction;
        if (attackAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await attackAction.writeTo(stream);
        }
        let repairAction = this.repairAction;
        if (repairAction === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await repairAction.writeTo(stream);
        }
    }
}
module.exports = EntityAction
