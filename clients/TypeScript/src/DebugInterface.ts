import {ClientMessage} from './model/ClientMessage';
import {DebugCommand} from './model/DebugCommand';
import {DebugState} from './model/DebugState';
import {StreamWrapper} from './StreamWrapper';

export class DebugInterface {
    constructor(
        private streamWrapper: StreamWrapper,
    ) {}

    async send(command: DebugCommand) {
        await (new ClientMessage.DebugMessage(command)).writeTo(this.streamWrapper);
        // TODO: only flush stream once here?
    }

    async getState() {
        await (new ClientMessage.RequestDebugState()).writeTo(this.streamWrapper);
        // TODO: only flush stream once here?
        return await DebugState.readFrom(this.streamWrapper);
    }
}
