package model;

import util.StreamUtil;

public class AttackAction {
    private Integer target;
    public Integer getTarget() { return target; }
    public void setTarget(Integer target) { this.target = target; }
    private model.AutoAttack autoAttack;
    public model.AutoAttack getAutoAttack() { return autoAttack; }
    public void setAutoAttack(model.AutoAttack autoAttack) { this.autoAttack = autoAttack; }
    public AttackAction() {}
    public AttackAction(Integer target, model.AutoAttack autoAttack) {
        this.target = target;
        this.autoAttack = autoAttack;
    }
    public static AttackAction readFrom(java.io.InputStream stream) throws java.io.IOException {
        AttackAction result = new AttackAction();
        if (StreamUtil.readBoolean(stream)) {
            result.target = StreamUtil.readInt(stream);
        } else {
            result.target = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.autoAttack = model.AutoAttack.readFrom(stream);
        } else {
            result.autoAttack = null;
        }
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        if (target == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, target);
        }
        if (autoAttack == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            autoAttack.writeTo(stream);
        }
    }
}
