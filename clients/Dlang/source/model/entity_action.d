import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct EntityAction {
    Nullable!(MoveAction) moveAction;
    Nullable!(BuildAction) buildAction;
    Nullable!(AttackAction) attackAction;
    Nullable!(RepairAction) repairAction;
    this(Nullable!(MoveAction) moveAction, Nullable!(BuildAction) buildAction, Nullable!(AttackAction) attackAction, Nullable!(RepairAction) repairAction) {
        this.moveAction = moveAction;
        this.buildAction = buildAction;
        this.attackAction = attackAction;
        this.repairAction = repairAction;
    }
    static EntityAction readFrom(Stream reader) {
        auto result = EntityAction();
        if (reader.readBool()) {
            result.moveAction = MoveAction.readFrom(reader);
        } else {
            result.moveAction.nullify();
        }
        if (reader.readBool()) {
            result.buildAction = BuildAction.readFrom(reader);
        } else {
            result.buildAction.nullify();
        }
        if (reader.readBool()) {
            result.attackAction = AttackAction.readFrom(reader);
        } else {
            result.attackAction.nullify();
        }
        if (reader.readBool()) {
            result.repairAction = RepairAction.readFrom(reader);
        } else {
            result.repairAction.nullify();
        }
        return result;
    }
    void writeTo(Stream writer) const {
        if (moveAction.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            moveAction.get.writeTo(writer);
        }
        if (buildAction.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            buildAction.get.writeTo(writer);
        }
        if (attackAction.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            attackAction.get.writeTo(writer);
        }
        if (repairAction.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            repairAction.get.writeTo(writer);
        }
    }
    string toString() const {
        return "EntityAction" ~ "(" ~
            to!string(moveAction) ~
            to!string(buildAction) ~
            to!string(attackAction) ~
            to!string(repairAction) ~
            ")";
    }
}
