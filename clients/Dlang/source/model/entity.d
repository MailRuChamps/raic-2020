import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct Entity {
    int id;
    Nullable!(int) playerId;
    EntityType entityType;
    Vec2Int position;
    int health;
    bool active;
    this(int id, Nullable!(int) playerId, EntityType entityType, Vec2Int position, int health, bool active) {
        this.id = id;
        this.playerId = playerId;
        this.entityType = entityType;
        this.position = position;
        this.health = health;
        this.active = active;
    }
    static Entity readFrom(Stream reader) {
        auto result = Entity();
        result.id = reader.readInt();
        if (reader.readBool()) {
            result.playerId = reader.readInt();
        } else {
            result.playerId.nullify();
        }
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
        result.health = reader.readInt();
        result.active = reader.readBool();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(id);
        if (playerId.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            writer.write(playerId.get);
        }
        writer.write(cast(int)(entityType));
        position.writeTo(writer);
        writer.write(health);
        writer.write(active);
    }
    string toString() const {
        return "Entity" ~ "(" ~
            to!string(id) ~
            to!string(playerId) ~
            to!string(entityType) ~
            to!string(position) ~
            to!string(health) ~
            to!string(active) ~
            ")";
    }
}
