import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct BuildAction {
    EntityType entityType;
    Vec2Int position;
    this(EntityType entityType, Vec2Int position) {
        this.entityType = entityType;
        this.position = position;
    }
    static BuildAction readFrom(Stream reader) {
        auto result = BuildAction();
        switch (reader.readInt()) {
        case 0:
            result.entityType = EntityType.Wall;
            break;
        case 1:
            result.entityType = EntityType.House;
            break;
        case 2:
            result.entityType = EntityType.BuilderBase;
            break;
        case 3:
            result.entityType = EntityType.BuilderUnit;
            break;
        case 4:
            result.entityType = EntityType.MeleeBase;
            break;
        case 5:
            result.entityType = EntityType.MeleeUnit;
            break;
        case 6:
            result.entityType = EntityType.RangedBase;
            break;
        case 7:
            result.entityType = EntityType.RangedUnit;
            break;
        case 8:
            result.entityType = EntityType.Resource;
            break;
        case 9:
            result.entityType = EntityType.Turret;
            break;
        default:
            throw new Exception("Unexpected tag value");
        }
        result.position = Vec2Int.readFrom(reader);
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(cast(int)(entityType));
        position.writeTo(writer);
    }
    string toString() const {
        return "BuildAction" ~ "(" ~
            to!string(entityType) ~
            to!string(position) ~
            ")";
    }
}
