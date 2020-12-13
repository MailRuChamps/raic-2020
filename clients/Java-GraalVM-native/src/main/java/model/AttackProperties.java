package model;

import util.StreamUtil;

public class AttackProperties {
    private int attackRange;
    public int getAttackRange() { return attackRange; }
    public void setAttackRange(int attackRange) { this.attackRange = attackRange; }
    private int damage;
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    private boolean collectResource;
    public boolean isCollectResource() { return collectResource; }
    public void setCollectResource(boolean collectResource) { this.collectResource = collectResource; }
    public AttackProperties() {}
    public AttackProperties(int attackRange, int damage, boolean collectResource) {
        this.attackRange = attackRange;
        this.damage = damage;
        this.collectResource = collectResource;
    }
    public static AttackProperties readFrom(java.io.InputStream stream) throws java.io.IOException {
        AttackProperties result = new AttackProperties();
        result.attackRange = StreamUtil.readInt(stream);
        result.damage = StreamUtil.readInt(stream);
        result.collectResource = StreamUtil.readBoolean(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, attackRange);
        StreamUtil.writeInt(stream, damage);
        StreamUtil.writeBoolean(stream, collectResource);
    }
}
