import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct RepairAction {
    int target;
    this(int target) {
        this.target = target;
    }
    static RepairAction readFrom(Stream reader) {
        auto result = RepairAction();
        result.target = reader.readInt();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(target);
    }
    string toString() const {
        return "RepairAction" ~ "(" ~
            to!string(target) ~
            ")";
    }
}
