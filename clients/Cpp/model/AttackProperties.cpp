#include "AttackProperties.hpp"

AttackProperties::AttackProperties() { }
AttackProperties::AttackProperties(int attackRange, int damage, bool collectResource) : attackRange(attackRange), damage(damage), collectResource(collectResource) { }
AttackProperties AttackProperties::readFrom(InputStream& stream) {
    AttackProperties result;
    result.attackRange = stream.readInt();
    result.damage = stream.readInt();
    result.collectResource = stream.readBool();
    return result;
}
void AttackProperties::writeTo(OutputStream& stream) const {
    stream.write(attackRange);
    stream.write(damage);
    stream.write(collectResource);
}
bool AttackProperties::operator ==(const AttackProperties& other) const {
    return attackRange == other.attackRange && damage == other.damage && collectResource == other.collectResource;
}
size_t std::hash<AttackProperties>::operator ()(const AttackProperties& value) const {
    size_t result = 0;
    result ^= std::hash<int>{}(value.attackRange) + 0x9e3779b9 + (result<<6) + (result>>2);
    result ^= std::hash<int>{}(value.damage) + 0x9e3779b9 + (result<<6) + (result>>2);
    result ^= std::hash<bool>{}(value.collectResource) + 0x9e3779b9 + (result<<6) + (result>>2);
    return result;
}
