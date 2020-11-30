const Vec2Int = require('./vec2-int');
class Entity {
    constructor(id, playerId, entityType, position, health, active) {
        this.id = id;
        this.playerId = playerId;
        this.entityType = entityType;
        this.position = position;
        this.health = health;
        this.active = active;
    }
    static async readFrom(stream) {
        let id;
        id = await stream.readInt();
        let playerId;
        if (await stream.readBool()) {
            playerId = await stream.readInt();
        } else {
            playerId = null;
        }
        let entityType;
        entityType = await stream.readInt();
        let position;
        position = await Vec2Int.readFrom(stream);
        let health;
        health = await stream.readInt();
        let active;
        active = await stream.readBool();
        return new Entity(id, playerId, entityType, position, health, active);
    }
    async writeTo(stream) {
        let id = this.id;
        await stream.writeInt(id);
        let playerId = this.playerId;
        if (playerId === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(playerId);
        }
        let entityType = this.entityType;
        await stream.writeInt(entityType);
        let position = this.position;
        await position.writeTo(stream);
        let health = this.health;
        await stream.writeInt(health);
        let active = this.active;
        await stream.writeBool(active);
    }
}
module.exports = Entity
