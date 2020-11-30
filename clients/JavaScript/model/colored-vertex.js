const Vec2Float = require('./vec2-float');
const Color = require('./color');
class ColoredVertex {
    constructor(worldPos, screenOffset, color) {
        this.worldPos = worldPos;
        this.screenOffset = screenOffset;
        this.color = color;
    }
    static async readFrom(stream) {
        let worldPos;
        if (await stream.readBool()) {
            worldPos = await Vec2Float.readFrom(stream);
        } else {
            worldPos = null;
        }
        let screenOffset;
        screenOffset = await Vec2Float.readFrom(stream);
        let color;
        color = await Color.readFrom(stream);
        return new ColoredVertex(worldPos, screenOffset, color);
    }
    async writeTo(stream) {
        let worldPos = this.worldPos;
        if (worldPos === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await worldPos.writeTo(stream);
        }
        let screenOffset = this.screenOffset;
        await screenOffset.writeTo(stream);
        let color = this.color;
        await color.writeTo(stream);
    }
}
module.exports = ColoredVertex
