import {DebugData} from './DebugData';
import {Stream} from './Stream';

export abstract class DebugCommand {
    static Add: typeof Add;
    static Clear: typeof Clear;

    static async readFrom(stream: Stream) {
        const tag = await stream.readInt();

        if (tag == Add.TAG) return await Add.readFrom(stream);
        if (tag == Clear.TAG) return await Clear.readFrom(stream);

        throw new Error('Unexpected tag value');
    }

    abstract async writeTo(stream: Stream): Promise<void>;
}

class Add extends DebugCommand {
    static TAG = 0;

    constructor(
        private data: DebugData,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const data = await DebugData.readFrom(stream);
        return new Add(data);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(Add.TAG);
        await this.data.writeTo(stream);
    }
}
DebugCommand.Add = Add;

class Clear extends DebugCommand {
    static TAG = 1;

    static async readFrom(_stream: Stream) {
        return new Clear();
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(Clear.TAG);
    }
}
DebugCommand.Clear = Clear;
