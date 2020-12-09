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
            case SetAutoFlush.TAG:
                return SetAutoFlush.readFrom(reader);
            case Flush.TAG:
                return Flush.readFrom(reader);
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

    static class SetAutoFlush : DebugCommand {
        static const int TAG = 2;
        bool enable;
        this() {}
        this(bool enable) {
            this.enable = enable;
        }
        static SetAutoFlush readFrom(Stream reader) {
            auto result = new SetAutoFlush();
            result.enable = reader.readBool();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            writer.write(enable);
        }
        override string toString() const {
            return "SetAutoFlush" ~ "(" ~
                to!string(enable) ~
                ")";
        }
    }

    static class Flush : DebugCommand {
        static const int TAG = 3;
        this() {}
        static Flush readFrom(Stream reader) {
            auto result = new Flush();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
        override string toString() const {
            return "Flush" ~ "(" ~
                ")";
        }
    }
}
