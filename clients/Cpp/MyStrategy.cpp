#include "MyStrategy.hpp"
#include <exception>

MyStrategy::MyStrategy() {}

Action MyStrategy::getAction(const PlayerView& playerView, DebugInterface* debugInterface)
{
    Action result = Action(std::unordered_map<int, EntityAction>());
    int myId = playerView.myId;
    for (size_t i = 0; i < playerView.entities.size(); i++) {
        const Entity& entity = playerView.entities[i];
        if (entity.playerId == nullptr || *entity.playerId != myId) {
            continue;
        }
        const EntityProperties& properties = playerView.entityProperties.at(entity.entityType);

        std::shared_ptr<MoveAction> moveAction = nullptr;
        std::shared_ptr<BuildAction> buildAction = nullptr;
        if (properties.canMove) {
            moveAction = std::shared_ptr<MoveAction>(new MoveAction(
                Vec2Int(playerView.mapSize - 1, playerView.mapSize - 1),
                true,
                true));
        } else if (properties.build != nullptr) {
            EntityType entityType = properties.build->options[0];
            size_t currentUnits = 0;
            for (size_t j = 0; j < playerView.entities.size(); j++) {
                if (playerView.entities[j].playerId != nullptr && *playerView.entities[j].playerId == myId
                    && playerView.entities[j].entityType == entityType) {
                    currentUnits++;
                }
            }
            if ((currentUnits + 1) * playerView.entityProperties.at(entityType).populationUse <= properties.populationProvide) {
                buildAction = std::shared_ptr<BuildAction>(new BuildAction(
                    entityType,
                    Vec2Int(entity.position.x + properties.size, entity.position.y + properties.size - 1)));
            }
        }
        std::vector<EntityType> validAutoAttackTargets;
        if (entity.entityType == BUILDER_UNIT) {
            validAutoAttackTargets.push_back(RESOURCE);
        }
        result.entityActions[entity.id] = EntityAction(
            moveAction,
            buildAction,
            std::shared_ptr<AttackAction>(new AttackAction(
                nullptr, std::shared_ptr<AutoAttack>(new AutoAttack(properties.sightRange, validAutoAttackTargets)))),
            nullptr);
    }
    return result;
}

void MyStrategy::debugUpdate(const PlayerView& playerView, DebugInterface& debugInterface)
{
    debugInterface.send(DebugCommand::Clear());
    debugInterface.getState();
}