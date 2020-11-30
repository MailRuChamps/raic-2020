import model;
import stream;
import std.conv;
import std.typecons : Nullable;

abstract class ClientMessage {
    abstract void writeTo(Stream writer) const;
    static ClientMessage readFrom(Stream reader) {
        switch (reader.readInt()) {
            case DebugMessage.TAG:
                return DebugMessage.readFrom(reader);
            case ActionMessage.TAG:
                return ActionMessage.readFrom(reader);
            case DebugUpdateDone.TAG:
                return DebugUpdateDone.readFrom(reader);
            case RequestDebugState.TAG:
                return RequestDebugState.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }

    static class DebugMessage : ClientMessage {
        static const int TAG = 0;
        DebugCommand command;
        this() {}
        this(DebugCommand command) {
            this.command = command;
        }
        static DebugMessage readFrom(Stream reader) {
            auto result = new DebugMessage();
            result.command = DebugCommand.readFrom(reader);
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            command.writeTo(writer);
        }
        override string toString() const {
            return "DebugMessage" ~ "(" ~
                to!string(command) ~
                ")";
        }
    }

    static class ActionMessage : ClientMessage {
        static const int TAG = 1;
        Action action;
        this() {}
        this(Action action) {
            this.action = action;
        }
        static ActionMessage readFrom(Stream reader) {
            auto result = new ActionMessage();
            result.action = Action.readFrom(reader);
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            action.writeTo(writer);
        }
        override string toString() const {
            return "ActionMessage" ~ "(" ~
                to!string(action) ~
                ")";
        }
    }

    static class DebugUpdateDone : ClientMessage {
        static const int TAG = 2;
        this() {}
        static DebugUpdateDone readFrom(Stream reader) {
            auto result = new DebugUpdateDone();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
        override string toString() const {
            return "DebugUpdateDone" ~ "(" ~
                ")";
        }
    }

    static class RequestDebugState : ClientMessage {
        static const int TAG = 3;
        this() {}
        static RequestDebugState readFrom(Stream reader) {
            auto result = new RequestDebugState();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
        override string toString() const {
            return "RequestDebugState" ~ "(" ~
                ")";
        }
    }
}
