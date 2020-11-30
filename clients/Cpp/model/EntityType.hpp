#ifndef _MODEL_ENTITY_TYPE_HPP_
#define _MODEL_ENTITY_TYPE_HPP_

#include "../Stream.hpp"

enum EntityType {
    WALL = 0,
    HOUSE = 1,
    BUILDER_BASE = 2,
    BUILDER_UNIT = 3,
    MELEE_BASE = 4,
    MELEE_UNIT = 5,
    RANGED_BASE = 6,
    RANGED_UNIT = 7,
    RESOURCE = 8,
    TURRET = 9
};

#endif
