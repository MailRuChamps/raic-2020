import model.*;

public class MyStrategy {
    public Action getAction(PlayerView playerView, DebugInterface debugInterface) {
        Action result = new Action(new java.util.HashMap<>());
        int myId = playerView.getMyId();
        for (Entity entity : playerView.getEntities()) {
            if (entity.getPlayerId() == null || entity.getPlayerId() != myId) {
                continue;
            }
            EntityProperties properties = playerView.getEntityProperties().get(entity.getEntityType());

            MoveAction moveAction = null;
            BuildAction buildAction = null;
            if (properties.isCanMove()) {
                moveAction = new MoveAction(
                    new Vec2Int(playerView.getMapSize() - 1, playerView.getMapSize() - 1),
                    true,
                    true);
            } else if (properties.getBuild() != null) {
                EntityType entityType = properties.getBuild().getOptions()[0];
                int currentUnits = 0;
                for (Entity otherEntity : playerView.getEntities()) {
                    if (otherEntity.getPlayerId() != null && otherEntity.getPlayerId() == myId
                        && otherEntity.getEntityType() == entityType) {
                        currentUnits++;
                    }
                }
                if ((currentUnits + 1) * playerView.getEntityProperties().get(entityType).getPopulationUse() <= properties.getPopulationProvide()) {
                    buildAction = new BuildAction(
                        entityType,
                        new Vec2Int(
                            entity.getPosition().getX() + properties.getSize(),
                            entity.getPosition().getY() + properties.getSize() - 1
                        )
                    );
                }
            }
            EntityType[] validAutoAttackTargets;
            if (entity.getEntityType() == EntityType.BUILDER_UNIT) {
                validAutoAttackTargets = new EntityType[] { EntityType.RESOURCE };
            } else {
                validAutoAttackTargets = new EntityType[0];
            }
            result.getEntityActions().put(entity.getId(), new EntityAction(
                moveAction,
                buildAction,
                new AttackAction(
                    null, new AutoAttack(properties.getSightRange(), validAutoAttackTargets)
                ),
                null
            ));
        }
        return result;
    }
    public void debugUpdate(PlayerView playerView, DebugInterface debugInterface) {
        debugInterface.send(new DebugCommand.Clear());
        debugInterface.getState();
    }
}