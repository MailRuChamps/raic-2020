import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct PlayerView {
    int myId;
    int mapSize;
    bool fogOfWar;
    EntityProperties[EntityType] entityProperties;
    int maxTickCount;
    int maxPathfindNodes;
    int currentTick;
    Player[] players;
    Entity[] entities;
    this(int myId, int mapSize, bool fogOfWar, EntityProperties[EntityType] entityProperties, int maxTickCount, int maxPathfindNodes, int currentTick, Player[] players, Entity[] entities) {
        this.myId = myId;
        this.mapSize = mapSize;
        this.fogOfWar = fogOfWar;
        this.entityProperties = entityProperties;
        this.maxTickCount = maxTickCount;
        this.maxPathfindNodes = maxPathfindNodes;
        this.currentTick = currentTick;
        this.players = players;
        this.entities = entities;
    }
    static PlayerView readFrom(Stream reader) {
        auto result = PlayerView();
        result.myId = reader.readInt();
        result.mapSize = reader.readInt();
        result.fogOfWar = reader.readBool();
        int entityPropertiesSize = reader.readInt();
        result.entityProperties.clear();
        for (int i = 0; i < entityPropertiesSize; i++) {
            EntityType entityPropertiesKey;
            switch (reader.readInt()) {
            case 0:
                entityPropertiesKey = EntityType.Wall;
                break;
            case 1:
                entityPropertiesKey = EntityType.House;
                break;
            case 2:
                entityPropertiesKey = EntityType.BuilderBase;
                break;
            case 3:
                entityPropertiesKey = EntityType.BuilderUnit;
                break;
            case 4:
                entityPropertiesKey = EntityType.MeleeBase;
                break;
            case 5:
                entityPropertiesKey = EntityType.MeleeUnit;
                break;
            case 6:
                entityPropertiesKey = EntityType.RangedBase;
                break;
            case 7:
                entityPropertiesKey = EntityType.RangedUnit;
                break;
            case 8:
                entityPropertiesKey = EntityType.Resource;
                break;
            case 9:
                entityPropertiesKey = EntityType.Turret;
                break;
            default:
                throw new Exception("Unexpected tag value");
            }
            EntityProperties entityPropertiesValue;
            entityPropertiesValue = EntityProperties.readFrom(reader);
            result.entityProperties[entityPropertiesKey] = entityPropertiesValue;
        }
        result.maxTickCount = reader.readInt();
        result.maxPathfindNodes = reader.readInt();
        result.currentTick = reader.readInt();
        result.players = new Player[reader.readInt()];
        for (int i = 0; i < result.players.length; i++) {
            result.players[i] = Player.readFrom(reader);
        }
        result.entities = new Entity[reader.readInt()];
        for (int i = 0; i < result.entities.length; i++) {
            result.entities[i] = Entity.readFrom(reader);
        }
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(myId);
        writer.write(mapSize);
        writer.write(fogOfWar);
        writer.write(cast(int)(entityProperties.length));
        foreach (entityPropertiesKey, entityPropertiesValue; entityProperties) {
            writer.write(cast(int)(entityPropertiesKey));
            entityPropertiesValue.writeTo(writer);
        }
        writer.write(maxTickCount);
        writer.write(maxPathfindNodes);
        writer.write(currentTick);
        writer.write(cast(int)(players.length));
        foreach (playersElement; players) {
            playersElement.writeTo(writer);
        }
        writer.write(cast(int)(entities.length));
        foreach (entitiesElement; entities) {
            entitiesElement.writeTo(writer);
        }
    }
    string toString() const {
        return "PlayerView" ~ "(" ~
            to!string(myId) ~
            to!string(mapSize) ~
            to!string(fogOfWar) ~
            to!string(entityProperties) ~
            to!string(maxTickCount) ~
            to!string(maxPathfindNodes) ~
            to!string(currentTick) ~
            to!string(players) ~
            to!string(entities) ~
            ")";
    }
}
