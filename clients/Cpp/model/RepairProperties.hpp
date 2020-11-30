#ifndef _MODEL_REPAIR_PROPERTIES_HPP_
#define _MODEL_REPAIR_PROPERTIES_HPP_

#include "../Stream.hpp"
#include "EntityType.hpp"
#include <stdexcept>
#include <string>
#include <vector>

class RepairProperties {
public:
    std::vector<EntityType> validTargets;
    int power;
    RepairProperties();
    RepairProperties(std::vector<EntityType> validTargets, int power);
    static RepairProperties readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
