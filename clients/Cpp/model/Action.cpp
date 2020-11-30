#include "Action.hpp"

Action::Action() { }
Action::Action(std::unordered_map<int, EntityAction> entityActions) : entityActions(entityActions) { }
Action Action::readFrom(InputStream& stream) {
    Action result;
    size_t entityActionsSize = stream.readInt();
    result.entityActions = std::unordered_map<int, EntityAction>();
    result.entityActions.reserve(entityActionsSize);
    for (size_t i = 0; i < entityActionsSize; i++) {
        int entityActionsKey;
        entityActionsKey = stream.readInt();
        EntityAction entityActionsValue;
        entityActionsValue = EntityAction::readFrom(stream);
        result.entityActions.emplace(std::make_pair(entityActionsKey, entityActionsValue));
    }
    return result;
}
void Action::writeTo(OutputStream& stream) const {
    stream.write((int)(entityActions.size()));
    for (const auto& entityActionsEntry : entityActions) {
        stream.write(entityActionsEntry.first);
        entityActionsEntry.second.writeTo(stream);
    }
}
