class RepairProperties {
    constructor(validTargets, power) {
        this.validTargets = validTargets;
        this.power = power;
    }
    static async readFrom(stream) {
        let validTargets;
        validTargets = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let validTargetsElement;
            validTargetsElement = await stream.readInt();
            validTargets.push(validTargetsElement);
        }
        let power;
        power = await stream.readInt();
        return new RepairProperties(validTargets, power);
    }
    async writeTo(stream) {
        let validTargets = this.validTargets;
        await stream.writeInt(validTargets.length);
        for (let validTargetsElement of validTargets) {
            await stream.writeInt(validTargetsElement);
        }
        let power = this.power;
        await stream.writeInt(power);
    }
}
module.exports = RepairProperties
