const Vec2Int = require('./vec2-int');
class BuildAction {
    constructor(entityType, position) {
        this.entityType = entityType;
        this.position = position;
    }
    static async readFrom(stream) {
        let entityType;
        entityType = await stream.readInt();
        let position;
        position = await Vec2Int.readFrom(stream);
        return new BuildAction(entityType, position);
    }
    async writeTo(stream) {
        let entityType = this.entityType;
        await stream.writeInt(entityType);
        let position = this.position;
        await position.writeTo(stream);
    }
}
module.exports = BuildAction
