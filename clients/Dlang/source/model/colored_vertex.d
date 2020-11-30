import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct ColoredVertex {
    Nullable!(Vec2Float) worldPos;
    Vec2Float screenOffset;
    Color color;
    this(Nullable!(Vec2Float) worldPos, Vec2Float screenOffset, Color color) {
        this.worldPos = worldPos;
        this.screenOffset = screenOffset;
        this.color = color;
    }
    static ColoredVertex readFrom(Stream reader) {
        auto result = ColoredVertex();
        if (reader.readBool()) {
            result.worldPos = Vec2Float.readFrom(reader);
        } else {
            result.worldPos.nullify();
        }
        result.screenOffset = Vec2Float.readFrom(reader);
        result.color = Color.readFrom(reader);
        return result;
    }
    void writeTo(Stream writer) const {
        if (worldPos.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            worldPos.get.writeTo(writer);
        }
        screenOffset.writeTo(writer);
        color.writeTo(writer);
    }
    string toString() const {
        return "ColoredVertex" ~ "(" ~
            to!string(worldPos) ~
            to!string(screenOffset) ~
            to!string(color) ~
            ")";
    }
}
