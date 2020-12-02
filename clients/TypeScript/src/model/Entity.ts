import {Vec2Int} from './Vec2Int';
import {EntityType} from './EntityType'
import {Stream} from './Stream';

export class Entity {
    constructor(
        public id: number,
        public playerId: number | null,
        public entityType: EntityType,
        public position: Vec2Int,
        public health: number,
        public active: boolean,
    ) {}

    static async readFrom(stream: Stream) {
        const id = await stream.readInt();
        const playerId = await stream.readBool() ? await stream.readInt() : null;
        const entityType = await stream.readInt();
        const position = await Vec2Int.readFrom(stream);
        const health = await stream.readInt();
        const active = await stream.readBool();

        return new Entity(id, playerId, entityType, position, health, active);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(this.id);
        if (this.playerId === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(this.playerId);
        }
        await stream.writeInt(this.entityType);
        await this.position.writeTo(stream);
        await stream.writeInt(this.health);
        await stream.writeBool(this.active);
    }
}
