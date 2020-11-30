import model;
import stream;
import std.conv;
import std.typecons : Nullable;

abstract class ServerMessage {
    abstract void writeTo(Stream writer) const;
    static ServerMessage readFrom(Stream reader) {
        switch (reader.readInt()) {
            case GetAction.TAG:
                return GetAction.readFrom(reader);
            case Finish.TAG:
                return Finish.readFrom(reader);
            case DebugUpdate.TAG:
                return DebugUpdate.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }

    static class GetAction : ServerMessage {
        static const int TAG = 0;
        PlayerView playerView;
        bool debugAvailable;
        this() {}
        this(PlayerView playerView, bool debugAvailable) {
            this.playerView = playerView;
            this.debugAvailable = debugAvailable;
        }
        static GetAction readFrom(Stream reader) {
            auto result = new GetAction();
            result.playerView = PlayerView.readFrom(reader);
            result.debugAvailable = reader.readBool();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            playerView.writeTo(writer);
            writer.write(debugAvailable);
        }
        override string toString() const {
            return "GetAction" ~ "(" ~
                to!string(playerView) ~
                to!string(debugAvailable) ~
                ")";
        }
    }

    static class Finish : ServerMessage {
        static const int TAG = 1;
        this() {}
        static Finish readFrom(Stream reader) {
            auto result = new Finish();
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
        override string toString() const {
            return "Finish" ~ "(" ~
                ")";
        }
    }

    static class DebugUpdate : ServerMessage {
        static const int TAG = 2;
        PlayerView playerView;
        this() {}
        this(PlayerView playerView) {
            this.playerView = playerView;
        }
        static DebugUpdate readFrom(Stream reader) {
            auto result = new DebugUpdate();
            result.playerView = PlayerView.readFrom(reader);
            return result;
        }
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            playerView.writeTo(writer);
        }
        override string toString() const {
            return "DebugUpdate" ~ "(" ~
                to!string(playerView) ~
                ")";
        }
    }
}
