using Aicup2020.Model;

namespace Aicup2020
{
    public class MyStrategy
    {
        public Action GetAction(PlayerView playerView, DebugInterface debugInterface)
        {
            Action result = new Action(new System.Collections.Generic.Dictionary<int, Model.EntityAction>());
            int myId = playerView.MyId;
            foreach (var entity in playerView.Entities) {
                if (entity.PlayerId != myId) {
                    continue;
                }
                EntityProperties properties = playerView.EntityProperties[entity.EntityType];
                MoveAction? moveAction = null;
                BuildAction? buildAction = null;
                if (properties.CanMove) {
                    moveAction = new MoveAction(
                        new Vec2Int(playerView.MapSize - 1, playerView.MapSize - 1),
                        true,
                        true
                    );
                } else if (properties.Build != null) {
                    EntityType entityType = properties.Build.Value.Options[0];
                    int currentUnits = 0;
                    foreach (var otherEntity in playerView.Entities) {
                        if (otherEntity.PlayerId != null && otherEntity.PlayerId == myId
                            && otherEntity.EntityType == entityType) {
                            currentUnits++;
                        }
                    }
                    if ((currentUnits + 1) * playerView.EntityProperties[entityType].PopulationUse <= properties.PopulationProvide) {
                        buildAction = new BuildAction(
                            entityType,
                            new Vec2Int(entity.Position.X + properties.Size, entity.Position.Y + properties.Size - 1)
                        );
                    }
                }
                EntityType[] validAutoAttackTargets;
                if (entity.EntityType == EntityType.BuilderUnit) {
                    validAutoAttackTargets = new EntityType[] { EntityType.Resource };
                } else {
                    validAutoAttackTargets = new EntityType[0];
                }
                result.EntityActions[entity.Id] = new EntityAction(
                    moveAction,
                    buildAction,
                    new AttackAction(
                        null,
                        new AutoAttack(properties.SightRange, validAutoAttackTargets)
                    ),
                    null
                );
            }
            return result;
        }

        public void DebugUpdate(PlayerView playerView, DebugInterface debugInterface) 
        {
            debugInterface.Send(new DebugCommand.Clear());
            debugInterface.GetState();
        }
    }
}