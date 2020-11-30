const ColoredVertex = require('./colored-vertex');
class DebugData {
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == Log.TAG) {
            return await Log.readFrom(stream);
        }
        if (tag == Primitives.TAG) {
            return await Primitives.readFrom(stream);
        }
        if (tag == PlacedText.TAG) {
            return await PlacedText.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}

class Log extends DebugData {
    constructor(text) {
        super();
        this.text = text;
    }
    static async readFrom(stream) {
        let text;
        text = await stream.readString();
        return new Log(text);
    }
    async writeTo(stream) {
        await stream.writeInt(Log.TAG);
        let text = this.text;
        await stream.writeString(text);
    }
}
DebugData.Log = Log;
Log.TAG = 0;
class Primitives extends DebugData {
    constructor(vertices, primitiveType) {
        super();
        this.vertices = vertices;
        this.primitiveType = primitiveType;
    }
    static async readFrom(stream) {
        let vertices;
        vertices = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let verticesElement;
            verticesElement = await ColoredVertex.readFrom(stream);
            vertices.push(verticesElement);
        }
        let primitiveType;
        primitiveType = await stream.readInt();
        return new Primitives(vertices, primitiveType);
    }
    async writeTo(stream) {
        await stream.writeInt(Primitives.TAG);
        let vertices = this.vertices;
        await stream.writeInt(vertices.length);
        for (let verticesElement of vertices) {
            await verticesElement.writeTo(stream);
        }
        let primitiveType = this.primitiveType;
        await stream.writeInt(primitiveType);
    }
}
DebugData.Primitives = Primitives;
Primitives.TAG = 1;
class PlacedText extends DebugData {
    constructor(vertex, text, alignment, size) {
        super();
        this.vertex = vertex;
        this.text = text;
        this.alignment = alignment;
        this.size = size;
    }
    static async readFrom(stream) {
        let vertex;
        vertex = await ColoredVertex.readFrom(stream);
        let text;
        text = await stream.readString();
        let alignment;
        alignment = await stream.readFloat();
        let size;
        size = await stream.readFloat();
        return new PlacedText(vertex, text, alignment, size);
    }
    async writeTo(stream) {
        await stream.writeInt(PlacedText.TAG);
        let vertex = this.vertex;
        await vertex.writeTo(stream);
        let text = this.text;
        await stream.writeString(text);
        let alignment = this.alignment;
        await stream.writeFloat(alignment);
        let size = this.size;
        await stream.writeFloat(size);
    }
}
DebugData.PlacedText = PlacedText;
PlacedText.TAG = 2;
module.exports = DebugData;
