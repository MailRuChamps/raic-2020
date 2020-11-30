const model = require('./model/index');

class DebugInterface {
    constructor(streamWrapper) {
        this.streamWrapper = streamWrapper;
    }

    async send(command) {
        await (new model.ClientMessage.DebugMessage(command)).writeTo(this.streamWrapper);
        // TODO: only flush stream once here?
    }

    async getState() {
        await (new model.ClientMessage.RequestDebugState()).writeTo(this.streamWrapper);
        // TODO: only flush stream once here?
        return await model.DebugState.readFrom(this.streamWrapper);
    }
}

module.exports.DebugInterface = DebugInterface;