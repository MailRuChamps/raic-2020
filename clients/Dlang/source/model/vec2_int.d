import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct Vec2Int {
    int x;
    int y;
    this(int x, int y) {
        this.x = x;
        this.y = y;
    }
    static Vec2Int readFrom(Stream reader) {
        auto result = Vec2Int();
        result.x = reader.readInt();
        result.y = reader.readInt();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(x);
        writer.write(y);
    }
    string toString() const {
        return "Vec2Int" ~ "(" ~
            to!string(x) ~
            to!string(y) ~
            ")";
    }
}
