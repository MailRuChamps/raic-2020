import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct Player {
    int id;
    int score;
    int resource;
    this(int id, int score, int resource) {
        this.id = id;
        this.score = score;
        this.resource = resource;
    }
    static Player readFrom(Stream reader) {
        auto result = Player();
        result.id = reader.readInt();
        result.score = reader.readInt();
        result.resource = reader.readInt();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(id);
        writer.write(score);
        writer.write(resource);
    }
    string toString() const {
        return "Player" ~ "(" ~
            to!string(id) ~
            to!string(score) ~
            to!string(resource) ~
            ")";
    }
}
