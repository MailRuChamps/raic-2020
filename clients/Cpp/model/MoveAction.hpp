#ifndef _MODEL_MOVE_ACTION_HPP_
#define _MODEL_MOVE_ACTION_HPP_

#include "../Stream.hpp"
#include "Vec2Int.hpp"
#include <stdexcept>
#include <string>

class MoveAction {
public:
    Vec2Int target;
    bool findClosestPosition;
    bool breakThrough;
    MoveAction();
    MoveAction(Vec2Int target, bool findClosestPosition, bool breakThrough);
    static MoveAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
    bool operator ==(const MoveAction& other) const;
};
namespace std {
    template<>
    struct hash<MoveAction> {
        size_t operator ()(const MoveAction& value) const;
    };
}

#endif
