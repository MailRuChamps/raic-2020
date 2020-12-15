import {EntityAction} from './EntityAction';
import {Stream} from './Stream';

export class Action {
    constructor(
        public entityActions: Map<number, EntityAction>,
    ) {}

    static async readFrom(stream: Stream) {
        const entityActions = new Map<number, EntityAction>();

        for (let i = await stream.readInt(); i > 0; i--) {
            const entityActionsKey: number = await stream.readInt();
            const entityActionsValue = await EntityAction.readFrom(stream);

            entityActions.set(entityActionsKey, entityActionsValue);
        }

        return new Action(entityActions);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(this.entityActions.size);
        for (let [key, entityAction] of this.entityActions) {
            await stream.writeInt(key);
            await entityAction.writeTo(stream);
        }
    }
}
