import {Stream} from './Stream';
import {PlayerView} from './PlayerView';


export class ServerMessage {
    static GetAction: typeof GetAction;
    static Finish: typeof Finish;
    static DebugUpdate: typeof DebugUpdate;

    static async readFrom(stream: Stream) {
        let tag = await stream.readInt();
        if (tag == GetAction.TAG) return await GetAction.readFrom(stream);
        if (tag == Finish.TAG) return await Finish.readFrom(stream);
        if (tag == DebugUpdate.TAG) return await DebugUpdate.readFrom(stream);

        throw new Error('Unexpected tag value');
    }
}

class GetAction extends ServerMessage {
    static TAG = 0

    constructor(
        public playerView: PlayerView,
        public debugAvailable: boolean,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const playerView = await PlayerView.readFrom(stream);
        const debugAvailable = await stream.readBool();

        return new GetAction(playerView, debugAvailable);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(GetAction.TAG);

        this.playerView.writeTo(stream);
        await stream.writeBool(this.debugAvailable);
    }
}
ServerMessage.GetAction = GetAction;

class Finish extends ServerMessage {
    static TAG = 1

    static async readFrom(_stream: Stream) {
        return new Finish();
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(Finish.TAG);
    }
}
ServerMessage.Finish = Finish;

class DebugUpdate extends ServerMessage {
    static TAG = 2;

    constructor(
        public playerView: PlayerView,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        let playerView;
        playerView = await PlayerView.readFrom(stream);
        return new DebugUpdate(playerView);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(DebugUpdate.TAG);
        let playerView = this.playerView;
        await playerView.writeTo(stream);
    }
}
ServerMessage.DebugUpdate = DebugUpdate;
