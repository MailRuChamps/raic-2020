#include "PlayerView.hpp"

PlayerView::PlayerView() { }
PlayerView::PlayerView(int myId, int mapSize, bool fogOfWar, std::unordered_map<EntityType, EntityProperties> entityProperties, int maxTickCount, int maxPathfindNodes, int currentTick, std::vector<Player> players, std::vector<Entity> entities) : myId(myId), mapSize(mapSize), fogOfWar(fogOfWar), entityProperties(entityProperties), maxTickCount(maxTickCount), maxPathfindNodes(maxPathfindNodes), currentTick(currentTick), players(players), entities(entities) { }
PlayerView PlayerView::readFrom(InputStream& stream) {
    PlayerView result;
    result.myId = stream.readInt();
    result.mapSize = stream.readInt();
    result.fogOfWar = stream.readBool();
    size_t entityPropertiesSize = stream.readInt();
    result.entityProperties = std::unordered_map<EntityType, EntityProperties>();
    result.entityProperties.reserve(entityPropertiesSize);
    for (size_t i = 0; i < entityPropertiesSize; i++) {
        EntityType entityPropertiesKey;
        switch (stream.readInt()) {
        case 0:
            entityPropertiesKey = EntityType::WALL;
            break;
        case 1:
            entityPropertiesKey = EntityType::HOUSE;
            break;
        case 2:
            entityPropertiesKey = EntityType::BUILDER_BASE;
            break;
        case 3:
            entityPropertiesKey = EntityType::BUILDER_UNIT;
            break;
        case 4:
            entityPropertiesKey = EntityType::MELEE_BASE;
            break;
        case 5:
            entityPropertiesKey = EntityType::MELEE_UNIT;
            break;
        case 6:
            entityPropertiesKey = EntityType::RANGED_BASE;
            break;
        case 7:
            entityPropertiesKey = EntityType::RANGED_UNIT;
            break;
        case 8:
            entityPropertiesKey = EntityType::RESOURCE;
            break;
        case 9:
            entityPropertiesKey = EntityType::TURRET;
            break;
        default:
            throw std::runtime_error("Unexpected tag value");
        }
        EntityProperties entityPropertiesValue;
        entityPropertiesValue = EntityProperties::readFrom(stream);
        result.entityProperties.emplace(std::make_pair(entityPropertiesKey, entityPropertiesValue));
    }
    result.maxTickCount = stream.readInt();
    result.maxPathfindNodes = stream.readInt();
    result.currentTick = stream.readInt();
    result.players = std::vector<Player>(stream.readInt());
    for (size_t i = 0; i < result.players.size(); i++) {
        result.players[i] = Player::readFrom(stream);
    }
    result.entities = std::vector<Entity>(stream.readInt());
    for (size_t i = 0; i < result.entities.size(); i++) {
        result.entities[i] = Entity::readFrom(stream);
    }
    return result;
}
void PlayerView::writeTo(OutputStream& stream) const {
    stream.write(myId);
    stream.write(mapSize);
    stream.write(fogOfWar);
    stream.write((int)(entityProperties.size()));
    for (const auto& entityPropertiesEntry : entityProperties) {
        stream.write((int)(entityPropertiesEntry.first));
        entityPropertiesEntry.second.writeTo(stream);
    }
    stream.write(maxTickCount);
    stream.write(maxPathfindNodes);
    stream.write(currentTick);
    stream.write((int)(players.size()));
    for (const Player& playersElement : players) {
        playersElement.writeTo(stream);
    }
    stream.write((int)(entities.size()));
    for (const Entity& entitiesElement : entities) {
        entitiesElement.writeTo(stream);
    }
}
