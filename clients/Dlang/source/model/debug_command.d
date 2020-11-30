import model;
import stream;
import std.conv;
import std.typecons : Nullable;

abstract class DebugCommand {
    abstract void writeTo(Stream writer) const;
    static DebugCommand readFrom(Stream reader) {
        switch (reader.readInt()) {
            case Add.TAG:
                return Add.readFrom(reader);
            case Clear.TAG:
                return Clear.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }

    static class Add : DebugCommand {
        static const int TAG = 0;
        DebugData data;
        this() {}
        this(DebugData data) {
            this.data = data;
        }
        static Add readFrom(Stream reader) {
            auto result = new Add();
            result.data = DebugData.readFrom(reader);
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            data.writeTo(writer);
        }
        override string toString() const {
            return "Add" ~ "(" ~
                to!string(data) ~
                ")";
        }
    }

    static class Clear : DebugCommand {
        static const int TAG = 1;
        this() {}
        static Clear readFrom(Stream reader) {
            auto result = new Clear();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
        override string toString() const {
            return "Clear" ~ "(" ~
                ")";
        }
    }
}
