import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct MoveAction {
    Vec2Int target;
    bool findClosestPosition;
    bool breakThrough;
    this(Vec2Int target, bool findClosestPosition, bool breakThrough) {
        this.target = target;
        this.findClosestPosition = findClosestPosition;
        this.breakThrough = breakThrough;
    }
    static MoveAction readFrom(Stream reader) {
        auto result = MoveAction();
        result.target = Vec2Int.readFrom(reader);
        result.findClosestPosition = reader.readBool();
        result.breakThrough = reader.readBool();
        return result;
    }
    void writeTo(Stream writer) const {
        target.writeTo(writer);
        writer.write(findClosestPosition);
        writer.write(breakThrough);
    }
    string toString() const {
        return "MoveAction" ~ "(" ~
            to!string(target) ~
            to!string(findClosestPosition) ~
            to!string(breakThrough) ~
            ")";
    }
}
