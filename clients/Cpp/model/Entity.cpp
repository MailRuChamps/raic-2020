#include "Entity.hpp"

Entity::Entity() { }
Entity::Entity(int id, std::shared_ptr<int> playerId, EntityType entityType, Vec2Int position, int health, bool active) : id(id), playerId(playerId), entityType(entityType), position(position), health(health), active(active) { }
Entity Entity::readFrom(InputStream& stream) {
    Entity result;
    result.id = stream.readInt();
    if (stream.readBool()) {
        result.playerId = std::shared_ptr<int>(new int());
        *result.playerId = stream.readInt();
    } else {
        result.playerId = std::shared_ptr<int>();
    }
    switch (stream.readInt()) {
    case 0:
        result.entityType = EntityType::WALL;
        break;
    case 1:
        result.entityType = EntityType::HOUSE;
        break;
    case 2:
        result.entityType = EntityType::BUILDER_BASE;
        break;
    case 3:
        result.entityType = EntityType::BUILDER_UNIT;
        break;
    case 4:
        result.entityType = EntityType::MELEE_BASE;
        break;
    case 5:
        result.entityType = EntityType::MELEE_UNIT;
        break;
    case 6:
        result.entityType = EntityType::RANGED_BASE;
        break;
    case 7:
        result.entityType = EntityType::RANGED_UNIT;
        break;
    case 8:
        result.entityType = EntityType::RESOURCE;
        break;
    case 9:
        result.entityType = EntityType::TURRET;
        break;
    default:
        throw std::runtime_error("Unexpected tag value");
    }
    result.position = Vec2Int::readFrom(stream);
    result.health = stream.readInt();
    result.active = stream.readBool();
    return result;
}
void Entity::writeTo(OutputStream& stream) const {
    stream.write(id);
    if (playerId) {
        stream.write(true);
        stream.write((*playerId));
    } else {
        stream.write(false);
    }
    stream.write((int)(entityType));
    position.writeTo(stream);
    stream.write(health);
    stream.write(active);
}
