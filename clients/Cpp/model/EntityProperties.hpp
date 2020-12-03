#ifndef _MODEL_ENTITY_PROPERTIES_HPP_
#define _MODEL_ENTITY_PROPERTIES_HPP_

#include "../Stream.hpp"
#include "AttackProperties.hpp"
#include "BuildProperties.hpp"
#include "EntityType.hpp"
#include "RepairProperties.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class EntityProperties {
public:
    int size;
    int buildScore;
    int destroyScore;
    bool canMove;
    int populationProvide;
    int populationUse;
    int maxHealth;
    int initialCost;
    int sightRange;
    int resourcePerHealth;
    std::shared_ptr<BuildProperties> build;
    std::shared_ptr<AttackProperties> attack;
    std::shared_ptr<RepairProperties> repair;
    EntityProperties();
    EntityProperties(int size, int buildScore, int destroyScore, bool canMove, int populationProvide, int populationUse, int maxHealth, int initialCost, int sightRange, int resourcePerHealth, std::shared_ptr<BuildProperties> build, std::shared_ptr<AttackProperties> attack, std::shared_ptr<RepairProperties> repair);
    static EntityProperties readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
