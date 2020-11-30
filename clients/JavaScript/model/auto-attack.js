class AutoAttack {
    constructor(pathfindRange, validTargets) {
        this.pathfindRange = pathfindRange;
        this.validTargets = validTargets;
    }
    static async readFrom(stream) {
        let pathfindRange;
        pathfindRange = await stream.readInt();
        let validTargets;
        validTargets = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let validTargetsElement;
            validTargetsElement = await stream.readInt();
            validTargets.push(validTargetsElement);
        }
        return new AutoAttack(pathfindRange, validTargets);
    }
    async writeTo(stream) {
        let pathfindRange = this.pathfindRange;
        await stream.writeInt(pathfindRange);
        let validTargets = this.validTargets;
        await stream.writeInt(validTargets.length);
        for (let validTargetsElement of validTargets) {
            await stream.writeInt(validTargetsElement);
        }
    }
}
module.exports = AutoAttack
