import {ColoredVertex} from './ColoredVertex';
import {PrimitiveType} from './PrimitiveType';
import {Stream} from './Stream';

export abstract class DebugData {
    static Log: typeof Log;
    static Primitives: typeof Primitives;
    static PlacedText: typeof PlacedText;

    static async readFrom(stream: Stream) {
        const tag = await stream.readInt();

        if (tag == Log.TAG) return await Log.readFrom(stream);
        if (tag == Primitives.TAG) return await Primitives.readFrom(stream);
        if (tag == PlacedText.TAG) return await PlacedText.readFrom(stream);

        throw new Error('Unexpected tag value');
    }

    abstract async writeTo(stream: Stream): Promise<void>;
}

class Log extends DebugData {
    static TAG = 0

    constructor(
        private text: string,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const text = await stream.readString();
        return new Log(text);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(Log.TAG);
        await stream.writeString(this.text);
    }
}
DebugData.Log = Log;

class Primitives extends DebugData {
    static TAG = 1;

    constructor(
        private vertices: ColoredVertex[],
        private primitiveType: PrimitiveType,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const vertices = [];

        for (let i = await stream.readInt(); i > 0; i--) {
            const verticesElement = await ColoredVertex.readFrom(stream);
            vertices.push(verticesElement);
        }

        const primitiveType = await stream.readInt();
        return new Primitives(vertices, primitiveType);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(Primitives.TAG);

        await stream.writeInt(this.vertices.length);
        for (let verticesElement of this.vertices) {
            await verticesElement.writeTo(stream);
        }

        await stream.writeInt(this.primitiveType);
    }
}
DebugData.Primitives = Primitives;

class PlacedText extends DebugData {
    static TAG = 2

    constructor(
        private vertex: ColoredVertex,
        private text: string,
        private alignment: number,
        private size: number,
    ) {
        super();
    }

    static async readFrom(stream: Stream) {
        const vertex = await ColoredVertex.readFrom(stream);
        const text = await stream.readString();
        const alignment = await stream.readFloat();
        const size = await stream.readFloat();

        return new PlacedText(vertex, text, alignment, size);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(PlacedText.TAG);
        await this.vertex.writeTo(stream);
        await stream.writeString(this.text);
        await stream.writeFloat(this.alignment);
        await stream.writeFloat(this.size);
    }
}
DebugData.PlacedText = PlacedText;
