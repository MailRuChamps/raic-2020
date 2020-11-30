const Vec2Int = require('./vec2-int');
class MoveAction {
    constructor(target, findClosestPosition, breakThrough) {
        this.target = target;
        this.findClosestPosition = findClosestPosition;
        this.breakThrough = breakThrough;
    }
    static async readFrom(stream) {
        let target;
        target = await Vec2Int.readFrom(stream);
        let findClosestPosition;
        findClosestPosition = await stream.readBool();
        let breakThrough;
        breakThrough = await stream.readBool();
        return new MoveAction(target, findClosestPosition, breakThrough);
    }
    async writeTo(stream) {
        let target = this.target;
        await target.writeTo(stream);
        let findClosestPosition = this.findClosestPosition;
        await stream.writeBool(findClosestPosition);
        let breakThrough = this.breakThrough;
        await stream.writeBool(breakThrough);
    }
}
module.exports = MoveAction
