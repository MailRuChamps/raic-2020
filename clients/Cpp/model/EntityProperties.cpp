#include "EntityProperties.hpp"

EntityProperties::EntityProperties() { }
EntityProperties::EntityProperties(int size, int buildScore, int destroyScore, bool canMove, int populationProvide, int populationUse, int maxHealth, int cost, int sightRange, int resourcePerHealth, std::shared_ptr<BuildProperties> build, std::shared_ptr<AttackProperties> attack, std::shared_ptr<RepairProperties> repair) : size(size), buildScore(buildScore), destroyScore(destroyScore), canMove(canMove), populationProvide(populationProvide), populationUse(populationUse), maxHealth(maxHealth), cost(cost), sightRange(sightRange), resourcePerHealth(resourcePerHealth), build(build), attack(attack), repair(repair) { }
EntityProperties EntityProperties::readFrom(InputStream& stream) {
    EntityProperties result;
    result.size = stream.readInt();
    result.buildScore = stream.readInt();
    result.destroyScore = stream.readInt();
    result.canMove = stream.readBool();
    result.populationProvide = stream.readInt();
    result.populationUse = stream.readInt();
    result.maxHealth = stream.readInt();
    result.cost = stream.readInt();
    result.sightRange = stream.readInt();
    result.resourcePerHealth = stream.readInt();
    if (stream.readBool()) {
        result.build = std::shared_ptr<BuildProperties>(new BuildProperties());
        *result.build = BuildProperties::readFrom(stream);
    } else {
        result.build = std::shared_ptr<BuildProperties>();
    }
    if (stream.readBool()) {
        result.attack = std::shared_ptr<AttackProperties>(new AttackProperties());
        *result.attack = AttackProperties::readFrom(stream);
    } else {
        result.attack = std::shared_ptr<AttackProperties>();
    }
    if (stream.readBool()) {
        result.repair = std::shared_ptr<RepairProperties>(new RepairProperties());
        *result.repair = RepairProperties::readFrom(stream);
    } else {
        result.repair = std::shared_ptr<RepairProperties>();
    }
    return result;
}
void EntityProperties::writeTo(OutputStream& stream) const {
    stream.write(size);
    stream.write(buildScore);
    stream.write(destroyScore);
    stream.write(canMove);
    stream.write(populationProvide);
    stream.write(populationUse);
    stream.write(maxHealth);
    stream.write(cost);
    stream.write(sightRange);
    stream.write(resourcePerHealth);
    if (build) {
        stream.write(true);
        (*build).writeTo(stream);
    } else {
        stream.write(false);
    }
    if (attack) {
        stream.write(true);
        (*attack).writeTo(stream);
    } else {
        stream.write(false);
    }
    if (repair) {
        stream.write(true);
        (*repair).writeTo(stream);
    } else {
        stream.write(false);
    }
}
