#ifndef _MODEL_BUILD_ACTION_HPP_
#define _MODEL_BUILD_ACTION_HPP_

#include "../Stream.hpp"
#include "EntityType.hpp"
#include "Vec2Int.hpp"
#include <stdexcept>
#include <string>

class BuildAction {
public:
    EntityType entityType;
    Vec2Int position;
    BuildAction();
    BuildAction(EntityType entityType, Vec2Int position);
    static BuildAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
    bool operator ==(const BuildAction& other) const;
};
namespace std {
    template<>
    struct hash<BuildAction> {
        size_t operator ()(const BuildAction& value) const;
    };
}

#endif
