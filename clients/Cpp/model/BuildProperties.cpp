#include "BuildProperties.hpp"

BuildProperties::BuildProperties() { }
BuildProperties::BuildProperties(std::vector<EntityType> options, std::shared_ptr<int> initHealth) : options(options), initHealth(initHealth) { }
BuildProperties BuildProperties::readFrom(InputStream& stream) {
    BuildProperties result;
    result.options = std::vector<EntityType>(stream.readInt());
    for (size_t i = 0; i < result.options.size(); i++) {
        switch (stream.readInt()) {
        case 0:
            result.options[i] = EntityType::WALL;
            break;
        case 1:
            result.options[i] = EntityType::HOUSE;
            break;
        case 2:
            result.options[i] = EntityType::BUILDER_BASE;
            break;
        case 3:
            result.options[i] = EntityType::BUILDER_UNIT;
            break;
        case 4:
            result.options[i] = EntityType::MELEE_BASE;
            break;
        case 5:
            result.options[i] = EntityType::MELEE_UNIT;
            break;
        case 6:
            result.options[i] = EntityType::RANGED_BASE;
            break;
        case 7:
            result.options[i] = EntityType::RANGED_UNIT;
            break;
        case 8:
            result.options[i] = EntityType::RESOURCE;
            break;
        case 9:
            result.options[i] = EntityType::TURRET;
            break;
        default:
            throw std::runtime_error("Unexpected tag value");
        }
    }
    if (stream.readBool()) {
        result.initHealth = std::shared_ptr<int>(new int());
        *result.initHealth = stream.readInt();
    } else {
        result.initHealth = std::shared_ptr<int>();
    }
    return result;
}
void BuildProperties::writeTo(OutputStream& stream) const {
    stream.write((int)(options.size()));
    for (const EntityType& optionsElement : options) {
        stream.write((int)(optionsElement));
    }
    if (initHealth) {
        stream.write(true);
        stream.write((*initHealth));
    } else {
        stream.write(false);
    }
}
