#ifndef _MODEL_ACTION_HPP_
#define _MODEL_ACTION_HPP_

#include "../Stream.hpp"
#include "AttackAction.hpp"
#include "AutoAttack.hpp"
#include "BuildAction.hpp"
#include "EntityAction.hpp"
#include "EntityType.hpp"
#include "MoveAction.hpp"
#include "RepairAction.hpp"
#include "Vec2Int.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

class Action {
public:
    std::unordered_map<int, EntityAction> entityActions;
    Action();
    Action(std::unordered_map<int, EntityAction> entityActions);
    static Action readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
