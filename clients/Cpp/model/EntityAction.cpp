#include "EntityAction.hpp"

EntityAction::EntityAction() { }
EntityAction::EntityAction(std::shared_ptr<MoveAction> moveAction, std::shared_ptr<BuildAction> buildAction, std::shared_ptr<AttackAction> attackAction, std::shared_ptr<RepairAction> repairAction) : moveAction(moveAction), buildAction(buildAction), attackAction(attackAction), repairAction(repairAction) { }
EntityAction EntityAction::readFrom(InputStream& stream) {
    EntityAction result;
    if (stream.readBool()) {
        result.moveAction = std::shared_ptr<MoveAction>(new MoveAction());
        *result.moveAction = MoveAction::readFrom(stream);
    } else {
        result.moveAction = std::shared_ptr<MoveAction>();
    }
    if (stream.readBool()) {
        result.buildAction = std::shared_ptr<BuildAction>(new BuildAction());
        *result.buildAction = BuildAction::readFrom(stream);
    } else {
        result.buildAction = std::shared_ptr<BuildAction>();
    }
    if (stream.readBool()) {
        result.attackAction = std::shared_ptr<AttackAction>(new AttackAction());
        *result.attackAction = AttackAction::readFrom(stream);
    } else {
        result.attackAction = std::shared_ptr<AttackAction>();
    }
    if (stream.readBool()) {
        result.repairAction = std::shared_ptr<RepairAction>(new RepairAction());
        *result.repairAction = RepairAction::readFrom(stream);
    } else {
        result.repairAction = std::shared_ptr<RepairAction>();
    }
    return result;
}
void EntityAction::writeTo(OutputStream& stream) const {
    if (moveAction) {
        stream.write(true);
        (*moveAction).writeTo(stream);
    } else {
        stream.write(false);
    }
    if (buildAction) {
        stream.write(true);
        (*buildAction).writeTo(stream);
    } else {
        stream.write(false);
    }
    if (attackAction) {
        stream.write(true);
        (*attackAction).writeTo(stream);
    } else {
        stream.write(false);
    }
    if (repairAction) {
        stream.write(true);
        (*repairAction).writeTo(stream);
    } else {
        stream.write(false);
    }
}
