const DebugData = require('./debug-data');
class DebugCommand {
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == Add.TAG) {
            return await Add.readFrom(stream);
        }
        if (tag == Clear.TAG) {
            return await Clear.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}

class Add extends DebugCommand {
    constructor(data) {
        super();
        this.data = data;
    }
    static async readFrom(stream) {
        let data;
        data = await DebugData.readFrom(stream);
        return new Add(data);
    }
    async writeTo(stream) {
        await stream.writeInt(Add.TAG);
        let data = this.data;
        await data.writeTo(stream);
    }
}
DebugCommand.Add = Add;
Add.TAG = 0;
class Clear extends DebugCommand {
    constructor() {
        super();
    }
    static async readFrom(stream) {
        return new Clear();
    }
    async writeTo(stream) {
        await stream.writeInt(Clear.TAG);
    }
}
DebugCommand.Clear = Clear;
Clear.TAG = 1;
module.exports = DebugCommand;
