import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct Camera {
    Vec2Float center;
    float rotation;
    float attack;
    float distance;
    bool perspective;
    this(Vec2Float center, float rotation, float attack, float distance, bool perspective) {
        this.center = center;
        this.rotation = rotation;
        this.attack = attack;
        this.distance = distance;
        this.perspective = perspective;
    }
    static Camera readFrom(Stream reader) {
        auto result = Camera();
        result.center = Vec2Float.readFrom(reader);
        result.rotation = reader.readFloat();
        result.attack = reader.readFloat();
        result.distance = reader.readFloat();
        result.perspective = reader.readBool();
        return result;
    }
    void writeTo(Stream writer) const {
        center.writeTo(writer);
        writer.write(rotation);
        writer.write(attack);
        writer.write(distance);
        writer.write(perspective);
    }
    string toString() const {
        return "Camera" ~ "(" ~
            to!string(center) ~
            to!string(rotation) ~
            to!string(attack) ~
            to!string(distance) ~
            to!string(perspective) ~
            ")";
    }
}
