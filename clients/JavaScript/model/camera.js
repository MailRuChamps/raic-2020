const Vec2Float = require('./vec2-float');
class Camera {
    constructor(center, rotation, attack, distance, perspective) {
        this.center = center;
        this.rotation = rotation;
        this.attack = attack;
        this.distance = distance;
        this.perspective = perspective;
    }
    static async readFrom(stream) {
        let center;
        center = await Vec2Float.readFrom(stream);
        let rotation;
        rotation = await stream.readFloat();
        let attack;
        attack = await stream.readFloat();
        let distance;
        distance = await stream.readFloat();
        let perspective;
        perspective = await stream.readBool();
        return new Camera(center, rotation, attack, distance, perspective);
    }
    async writeTo(stream) {
        let center = this.center;
        await center.writeTo(stream);
        let rotation = this.rotation;
        await stream.writeFloat(rotation);
        let attack = this.attack;
        await stream.writeFloat(attack);
        let distance = this.distance;
        await stream.writeFloat(distance);
        let perspective = this.perspective;
        await stream.writeBool(perspective);
    }
}
module.exports = Camera
