class RepairAction {
    constructor(target) {
        this.target = target;
    }
    static async readFrom(stream) {
        let target;
        target = await stream.readInt();
        return new RepairAction(target);
    }
    async writeTo(stream) {
        let target = this.target;
        await stream.writeInt(target);
    }
}
module.exports = RepairAction
