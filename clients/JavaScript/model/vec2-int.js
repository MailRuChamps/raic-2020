class Vec2Int {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
    static async readFrom(stream) {
        let x;
        x = await stream.readInt();
        let y;
        y = await stream.readInt();
        return new Vec2Int(x, y);
    }
    async writeTo(stream) {
        let x = this.x;
        await stream.writeInt(x);
        let y = this.y;
        await stream.writeInt(y);
    }
}
module.exports = Vec2Int
