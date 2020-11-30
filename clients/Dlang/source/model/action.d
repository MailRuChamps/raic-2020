import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct Action {
    EntityAction[int] entityActions;
    this(EntityAction[int] entityActions) {
        this.entityActions = entityActions;
    }
    static Action readFrom(Stream reader) {
        auto result = Action();
        int entityActionsSize = reader.readInt();
        result.entityActions.clear();
        for (int i = 0; i < entityActionsSize; i++) {
            int entityActionsKey;
            entityActionsKey = reader.readInt();
            EntityAction entityActionsValue;
            entityActionsValue = EntityAction.readFrom(reader);
            result.entityActions[entityActionsKey] = entityActionsValue;
        }
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(cast(int)(entityActions.length));
        foreach (entityActionsKey, entityActionsValue; entityActions) {
            writer.write(entityActionsKey);
            entityActionsValue.writeTo(writer);
        }
    }
    string toString() const {
        return "Action" ~ "(" ~
            to!string(entityActions) ~
            ")";
    }
}
