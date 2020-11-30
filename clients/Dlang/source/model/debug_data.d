import model;
import stream;
import std.conv;
import std.typecons : Nullable;

abstract class DebugData {
    abstract void writeTo(Stream writer) const;
    static DebugData readFrom(Stream reader) {
        switch (reader.readInt()) {
            case Log.TAG:
                return Log.readFrom(reader);
            case Primitives.TAG:
                return Primitives.readFrom(reader);
            case PlacedText.TAG:
                return PlacedText.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }

    static class Log : DebugData {
        static const int TAG = 0;
        string text;
        this() {}
        this(string text) {
            this.text = text;
        }
        static Log readFrom(Stream reader) {
            auto result = new Log();
            result.text = reader.readString();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            writer.write(text);
        }
        override string toString() const {
            return "Log" ~ "(" ~
                to!string(text) ~
                ")";
        }
    }

    static class Primitives : DebugData {
        static const int TAG = 1;
        ColoredVertex[] vertices;
        PrimitiveType primitiveType;
        this() {}
        this(ColoredVertex[] vertices, PrimitiveType primitiveType) {
            this.vertices = vertices;
            this.primitiveType = primitiveType;
        }
        static Primitives readFrom(Stream reader) {
            auto result = new Primitives();
            result.vertices = new ColoredVertex[reader.readInt()];
            for (int i = 0; i < result.vertices.length; i++) {
                result.vertices[i] = ColoredVertex.readFrom(reader);
            }
            switch (reader.readInt()) {
            case 0:
                result.primitiveType = PrimitiveType.Lines;
                break;
            case 1:
                result.primitiveType = PrimitiveType.Triangles;
                break;
            default:
                throw new Exception("Unexpected tag value");
            }
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            writer.write(cast(int)(vertices.length));
            foreach (verticesElement; vertices) {
                verticesElement.writeTo(writer);
            }
            writer.write(cast(int)(primitiveType));
        }
        override string toString() const {
            return "Primitives" ~ "(" ~
                to!string(vertices) ~
                to!string(primitiveType) ~
                ")";
        }
    }

    static class PlacedText : DebugData {
        static const int TAG = 2;
        ColoredVertex vertex;
        string text;
        float alignment;
        float size;
        this() {}
        this(ColoredVertex vertex, string text, float alignment, float size) {
            this.vertex = vertex;
            this.text = text;
            this.alignment = alignment;
            this.size = size;
        }
        static PlacedText readFrom(Stream reader) {
            auto result = new PlacedText();
            result.vertex = ColoredVertex.readFrom(reader);
            result.text = reader.readString();
            result.alignment = reader.readFloat();
            result.size = reader.readFloat();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            vertex.writeTo(writer);
            writer.write(text);
            writer.write(alignment);
            writer.write(size);
        }
        override string toString() const {
            return "PlacedText" ~ "(" ~
                to!string(vertex) ~
                to!string(text) ~
                to!string(alignment) ~
                to!string(size) ~
                ")";
        }
    }
}
