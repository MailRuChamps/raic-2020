#ifndef _MODEL_ENTITY_ACTION_HPP_
#define _MODEL_ENTITY_ACTION_HPP_

#include "../Stream.hpp"
#include "AttackAction.hpp"
#include "AutoAttack.hpp"
#include "BuildAction.hpp"
#include "EntityType.hpp"
#include "MoveAction.hpp"
#include "RepairAction.hpp"
#include "Vec2Int.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class EntityAction {
public:
    std::shared_ptr<MoveAction> moveAction;
    std::shared_ptr<BuildAction> buildAction;
    std::shared_ptr<AttackAction> attackAction;
    std::shared_ptr<RepairAction> repairAction;
    EntityAction();
    EntityAction(std::shared_ptr<MoveAction> moveAction, std::shared_ptr<BuildAction> buildAction, std::shared_ptr<AttackAction> attackAction, std::shared_ptr<RepairAction> repairAction);
    static EntityAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
