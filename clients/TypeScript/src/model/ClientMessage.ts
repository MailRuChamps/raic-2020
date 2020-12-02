import {DebugCommand} from './DebugCommand';
import {Action} from './Action';
import {Stream} from './Stream';

export class ClientMessage {
    static DebugMessage: typeof DebugMessage;
    static ActionMessage: typeof ActionMessage;
    static DebugUpdateDone: typeof DebugUpdateDone;
    static RequestDebugState: typeof RequestDebugState;

    static async readFrom(stream: Stream) {
        const tag = await stream.readInt();

        if (tag == DebugMessage.TAG) return await DebugMessage.readFrom(stream);
        if (tag == ActionMessage.TAG) return await ActionMessage.readFrom(stream);
        if (tag == DebugUpdateDone.TAG) return await DebugUpdateDone.readFrom(stream);
        if (tag == RequestDebugState.TAG) return await RequestDebugState.readFrom(stream);

        throw new Error('Unexpected tag value');
    }
}

class DebugMessage extends ClientMessage {
    static TAG = 0;

    constructor(
        private command: DebugCommand,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const command = await DebugCommand.readFrom(stream);
        return new DebugMessage(command);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(DebugMessage.TAG);
        await this.command.writeTo(stream);
    }
}

class ActionMessage extends ClientMessage {
    static TAG = 1;

    constructor(
        private action: Action,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const action = await Action.readFrom(stream);
        return new ActionMessage(action);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(ActionMessage.TAG);
        await this.action.writeTo(stream);
    }
}

class DebugUpdateDone extends ClientMessage {
    static TAG = 2;

    static async readFrom(_stream: Stream) {
        return new DebugUpdateDone();
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(DebugUpdateDone.TAG);
    }
}

class RequestDebugState extends ClientMessage {
    static TAG = 3;

    static async readFrom(_stream: Stream) {
        return new RequestDebugState();
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(RequestDebugState.TAG);
    }
}

ClientMessage.DebugMessage = DebugMessage;
ClientMessage.ActionMessage = ActionMessage;
ClientMessage.DebugUpdateDone = DebugUpdateDone;
ClientMessage.RequestDebugState = RequestDebugState;
