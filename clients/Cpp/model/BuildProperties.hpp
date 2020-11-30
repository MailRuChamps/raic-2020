#ifndef _MODEL_BUILD_PROPERTIES_HPP_
#define _MODEL_BUILD_PROPERTIES_HPP_

#include "../Stream.hpp"
#include "EntityType.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class BuildProperties {
public:
    std::vector<EntityType> options;
    std::shared_ptr<int> initHealth;
    BuildProperties();
    BuildProperties(std::vector<EntityType> options, std::shared_ptr<int> initHealth);
    static BuildProperties readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
