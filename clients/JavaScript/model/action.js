const EntityAction = require('./entity-action');
class Action {
    constructor(entityActions) {
        this.entityActions = entityActions;
    }
    static async readFrom(stream) {
        let entityActions;
        entityActions = new Map();
        for (let i = await stream.readInt(); i > 0; i--) {
            let entityActionsKey;
            let entityActionsValue;
            entityActionsKey = await stream.readInt();
            entityActionsValue = await EntityAction.readFrom(stream);
            entityActions.set(entityActionsKey, entityActionsValue);
        }
        return new Action(entityActions);
    }
    async writeTo(stream) {
        let entityActions = this.entityActions;
        await stream.writeInt(entityActions.size);
        for (let entityActionsEntry of entityActions) {
            let entityActionsKey = entityActionsEntry[0];
            let entityActionsValue = entityActionsEntry[1];
            await stream.writeInt(entityActionsKey);
            await entityActionsValue.writeTo(stream);
        }
    }
}
module.exports = Action
