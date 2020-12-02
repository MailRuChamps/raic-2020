import {Vec2Int} from './Vec2Int';
import {EntityType} from './EntityType';
import {Stream} from './Stream';

export class BuildAction {
    constructor(private entityType: EntityType, private position: Vec2Int) {}

    static async readFrom(stream: Stream) {
        const entityType = await stream.readInt();
        const position = await Vec2Int.readFrom(stream);

        return new BuildAction(entityType, position);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(this.entityType);
        await this.position.writeTo(stream);
    }
}
