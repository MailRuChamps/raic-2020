package model;

import util.StreamUtil;

public class EntityAction {
    private model.MoveAction moveAction;
    public model.MoveAction getMoveAction() { return moveAction; }
    public void setMoveAction(model.MoveAction moveAction) { this.moveAction = moveAction; }
    private model.BuildAction buildAction;
    public model.BuildAction getBuildAction() { return buildAction; }
    public void setBuildAction(model.BuildAction buildAction) { this.buildAction = buildAction; }
    private model.AttackAction attackAction;
    public model.AttackAction getAttackAction() { return attackAction; }
    public void setAttackAction(model.AttackAction attackAction) { this.attackAction = attackAction; }
    private model.RepairAction repairAction;
    public model.RepairAction getRepairAction() { return repairAction; }
    public void setRepairAction(model.RepairAction repairAction) { this.repairAction = repairAction; }
    public EntityAction() {}
    public EntityAction(model.MoveAction moveAction, model.BuildAction buildAction, model.AttackAction attackAction, model.RepairAction repairAction) {
        this.moveAction = moveAction;
        this.buildAction = buildAction;
        this.attackAction = attackAction;
        this.repairAction = repairAction;
    }
    public static EntityAction readFrom(java.io.InputStream stream) throws java.io.IOException {
        EntityAction result = new EntityAction();
        if (StreamUtil.readBoolean(stream)) {
            result.moveAction = model.MoveAction.readFrom(stream);
        } else {
            result.moveAction = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.buildAction = model.BuildAction.readFrom(stream);
        } else {
            result.buildAction = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.attackAction = model.AttackAction.readFrom(stream);
        } else {
            result.attackAction = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.repairAction = model.RepairAction.readFrom(stream);
        } else {
            result.repairAction = null;
        }
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        if (moveAction == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            moveAction.writeTo(stream);
        }
        if (buildAction == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            buildAction.writeTo(stream);
        }
        if (attackAction == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            attackAction.writeTo(stream);
        }
        if (repairAction == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            repairAction.writeTo(stream);
        }
    }
}
