#ifndef _MODEL_AUTO_ATTACK_HPP_
#define _MODEL_AUTO_ATTACK_HPP_

#include "../Stream.hpp"
#include "EntityType.hpp"
#include <stdexcept>
#include <string>
#include <vector>

class AutoAttack {
public:
    int pathfindRange;
    std::vector<EntityType> validTargets;
    AutoAttack();
    AutoAttack(int pathfindRange, std::vector<EntityType> validTargets);
    static AutoAttack readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
