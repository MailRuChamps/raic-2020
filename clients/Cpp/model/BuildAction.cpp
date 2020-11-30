#include "BuildAction.hpp"

BuildAction::BuildAction() { }
BuildAction::BuildAction(EntityType entityType, Vec2Int position) : entityType(entityType), position(position) { }
BuildAction BuildAction::readFrom(InputStream& stream) {
    BuildAction result;
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
    return result;
}
void BuildAction::writeTo(OutputStream& stream) const {
    stream.write((int)(entityType));
    position.writeTo(stream);
}
bool BuildAction::operator ==(const BuildAction& other) const {
    return entityType == other.entityType && position == other.position;
}
size_t std::hash<BuildAction>::operator ()(const BuildAction& value) const {
    size_t result = 0;
    result ^= std::hash<EntityType>{}(value.entityType) + 0x9e3779b9 + (result<<6) + (result>>2);
    result ^= std::hash<Vec2Int>{}(value.position) + 0x9e3779b9 + (result<<6) + (result>>2);
    return result;
}
