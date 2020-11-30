const DebugCommand = require('./debug-command');
const Action = require('./action');
class ClientMessage {
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == DebugMessage.TAG) {
            return await DebugMessage.readFrom(stream);
        }
        if (tag == ActionMessage.TAG) {
            return await ActionMessage.readFrom(stream);
        }
        if (tag == DebugUpdateDone.TAG) {
            return await DebugUpdateDone.readFrom(stream);
        }
        if (tag == RequestDebugState.TAG) {
            return await RequestDebugState.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}

class DebugMessage extends ClientMessage {
    constructor(command) {
        super();
        this.command = command;
    }
    static async readFrom(stream) {
        let command;
        command = await DebugCommand.readFrom(stream);
        return new DebugMessage(command);
    }
    async writeTo(stream) {
        await stream.writeInt(DebugMessage.TAG);
        let command = this.command;
        await command.writeTo(stream);
    }
}
ClientMessage.DebugMessage = DebugMessage;
DebugMessage.TAG = 0;
class ActionMessage extends ClientMessage {
    constructor(action) {
        super();
        this.action = action;
    }
    static async readFrom(stream) {
        let action;
        action = await Action.readFrom(stream);
        return new ActionMessage(action);
    }
    async writeTo(stream) {
        await stream.writeInt(ActionMessage.TAG);
        let action = this.action;
        await action.writeTo(stream);
    }
}
ClientMessage.ActionMessage = ActionMessage;
ActionMessage.TAG = 1;
class DebugUpdateDone extends ClientMessage {
    constructor() {
        super();
    }
    static async readFrom(stream) {
        return new DebugUpdateDone();
    }
    async writeTo(stream) {
        await stream.writeInt(DebugUpdateDone.TAG);
    }
}
ClientMessage.DebugUpdateDone = DebugUpdateDone;
DebugUpdateDone.TAG = 2;
class RequestDebugState extends ClientMessage {
    constructor() {
        super();
    }
    static async readFrom(stream) {
        return new RequestDebugState();
    }
    async writeTo(stream) {
        await stream.writeInt(RequestDebugState.TAG);
    }
}
ClientMessage.RequestDebugState = RequestDebugState;
RequestDebugState.TAG = 3;
module.exports = ClientMessage;
