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
        if (tag == SetAutoFlush.TAG) {
            return await SetAutoFlush.readFrom(stream);
        }
        if (tag == Flush.TAG) {
            return await Flush.readFrom(stream);
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
class SetAutoFlush extends DebugCommand {
    constructor(enable) {
        super();
        this.enable = enable;
    }
    static async readFrom(stream) {
        let enable;
        enable = await stream.readBool();
        return new SetAutoFlush(enable);
    }
    async writeTo(stream) {
        await stream.writeInt(SetAutoFlush.TAG);
        let enable = this.enable;
        await stream.writeBool(enable);
    }
}
DebugCommand.SetAutoFlush = SetAutoFlush;
SetAutoFlush.TAG = 2;
class Flush extends DebugCommand {
    constructor() {
        super();
    }
    static async readFrom(stream) {
        return new Flush();
    }
    async writeTo(stream) {
        await stream.writeInt(Flush.TAG);
    }
}
DebugCommand.Flush = Flush;
Flush.TAG = 3;
module.exports = DebugCommand;
