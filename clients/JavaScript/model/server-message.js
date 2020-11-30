const PlayerView = require('./player-view');
class ServerMessage {
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == GetAction.TAG) {
            return await GetAction.readFrom(stream);
        }
        if (tag == Finish.TAG) {
            return await Finish.readFrom(stream);
        }
        if (tag == DebugUpdate.TAG) {
            return await DebugUpdate.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}

class GetAction extends ServerMessage {
    constructor(playerView, debugAvailable) {
        super();
        this.playerView = playerView;
        this.debugAvailable = debugAvailable;
    }
    static async readFrom(stream) {
        let playerView;
        playerView = await PlayerView.readFrom(stream);
        let debugAvailable;
        debugAvailable = await stream.readBool();
        return new GetAction(playerView, debugAvailable);
    }
    async writeTo(stream) {
        await stream.writeInt(GetAction.TAG);
        let playerView = this.playerView;
        await playerView.writeTo(stream);
        let debugAvailable = this.debugAvailable;
        await stream.writeBool(debugAvailable);
    }
}
ServerMessage.GetAction = GetAction;
GetAction.TAG = 0;
class Finish extends ServerMessage {
    constructor() {
        super();
    }
    static async readFrom(stream) {
        return new Finish();
    }
    async writeTo(stream) {
        await stream.writeInt(Finish.TAG);
    }
}
ServerMessage.Finish = Finish;
Finish.TAG = 1;
class DebugUpdate extends ServerMessage {
    constructor(playerView) {
        super();
        this.playerView = playerView;
    }
    static async readFrom(stream) {
        let playerView;
        playerView = await PlayerView.readFrom(stream);
        return new DebugUpdate(playerView);
    }
    async writeTo(stream) {
        await stream.writeInt(DebugUpdate.TAG);
        let playerView = this.playerView;
        await playerView.writeTo(stream);
    }
}
ServerMessage.DebugUpdate = DebugUpdate;
DebugUpdate.TAG = 2;
module.exports = ServerMessage;
