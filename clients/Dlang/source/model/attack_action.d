import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct AttackAction {
    Nullable!(int) target;
    Nullable!(AutoAttack) autoAttack;
    this(Nullable!(int) target, Nullable!(AutoAttack) autoAttack) {
        this.target = target;
        this.autoAttack = autoAttack;
    }
    static AttackAction readFrom(Stream reader) {
        auto result = AttackAction();
        if (reader.readBool()) {
            result.target = reader.readInt();
        } else {
            result.target.nullify();
        }
        if (reader.readBool()) {
            result.autoAttack = AutoAttack.readFrom(reader);
        } else {
            result.autoAttack.nullify();
        }
        return result;
    }
    void writeTo(Stream writer) const {
        if (target.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            writer.write(target.get);
        }
        if (autoAttack.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            autoAttack.get.writeTo(writer);
        }
    }
    string toString() const {
        return "AttackAction" ~ "(" ~
            to!string(target) ~
            to!string(autoAttack) ~
            ")";
    }
}
