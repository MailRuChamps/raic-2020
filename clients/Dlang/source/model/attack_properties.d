import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct AttackProperties {
    int attackRange;
    int damage;
    bool collectResource;
    this(int attackRange, int damage, bool collectResource) {
        this.attackRange = attackRange;
        this.damage = damage;
        this.collectResource = collectResource;
    }
    static AttackProperties readFrom(Stream reader) {
        auto result = AttackProperties();
        result.attackRange = reader.readInt();
        result.damage = reader.readInt();
        result.collectResource = reader.readBool();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(attackRange);
        writer.write(damage);
        writer.write(collectResource);
    }
    string toString() const {
        return "AttackProperties" ~ "(" ~
            to!string(attackRange) ~
            to!string(damage) ~
            to!string(collectResource) ~
            ")";
    }
}
