package model;

import util.StreamUtil;

public class EntityProperties {
    private int size;
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    private int buildScore;
    public int getBuildScore() { return buildScore; }
    public void setBuildScore(int buildScore) { this.buildScore = buildScore; }
    private int destroyScore;
    public int getDestroyScore() { return destroyScore; }
    public void setDestroyScore(int destroyScore) { this.destroyScore = destroyScore; }
    private boolean canMove;
    public boolean isCanMove() { return canMove; }
    public void setCanMove(boolean canMove) { this.canMove = canMove; }
    private int populationProvide;
    public int getPopulationProvide() { return populationProvide; }
    public void setPopulationProvide(int populationProvide) { this.populationProvide = populationProvide; }
    private int populationUse;
    public int getPopulationUse() { return populationUse; }
    public void setPopulationUse(int populationUse) { this.populationUse = populationUse; }
    private int maxHealth;
    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    private int initialCost;
    public int getInitialCost() { return initialCost; }
    public void setInitialCost(int initialCost) { this.initialCost = initialCost; }
    private int sightRange;
    public int getSightRange() { return sightRange; }
    public void setSightRange(int sightRange) { this.sightRange = sightRange; }
    private int resourcePerHealth;
    public int getResourcePerHealth() { return resourcePerHealth; }
    public void setResourcePerHealth(int resourcePerHealth) { this.resourcePerHealth = resourcePerHealth; }
    private model.BuildProperties build;
    public model.BuildProperties getBuild() { return build; }
    public void setBuild(model.BuildProperties build) { this.build = build; }
    private model.AttackProperties attack;
    public model.AttackProperties getAttack() { return attack; }
    public void setAttack(model.AttackProperties attack) { this.attack = attack; }
    private model.RepairProperties repair;
    public model.RepairProperties getRepair() { return repair; }
    public void setRepair(model.RepairProperties repair) { this.repair = repair; }
    public EntityProperties() {}
    public EntityProperties(int size, int buildScore, int destroyScore, boolean canMove, int populationProvide, int populationUse, int maxHealth, int initialCost, int sightRange, int resourcePerHealth, model.BuildProperties build, model.AttackProperties attack, model.RepairProperties repair) {
        this.size = size;
        this.buildScore = buildScore;
        this.destroyScore = destroyScore;
        this.canMove = canMove;
        this.populationProvide = populationProvide;
        this.populationUse = populationUse;
        this.maxHealth = maxHealth;
        this.initialCost = initialCost;
        this.sightRange = sightRange;
        this.resourcePerHealth = resourcePerHealth;
        this.build = build;
        this.attack = attack;
        this.repair = repair;
    }
    public static EntityProperties readFrom(java.io.InputStream stream) throws java.io.IOException {
        EntityProperties result = new EntityProperties();
        result.size = StreamUtil.readInt(stream);
        result.buildScore = StreamUtil.readInt(stream);
        result.destroyScore = StreamUtil.readInt(stream);
        result.canMove = StreamUtil.readBoolean(stream);
        result.populationProvide = StreamUtil.readInt(stream);
        result.populationUse = StreamUtil.readInt(stream);
        result.maxHealth = StreamUtil.readInt(stream);
        result.initialCost = StreamUtil.readInt(stream);
        result.sightRange = StreamUtil.readInt(stream);
        result.resourcePerHealth = StreamUtil.readInt(stream);
        if (StreamUtil.readBoolean(stream)) {
            result.build = model.BuildProperties.readFrom(stream);
        } else {
            result.build = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.attack = model.AttackProperties.readFrom(stream);
        } else {
            result.attack = null;
        }
        if (StreamUtil.readBoolean(stream)) {
            result.repair = model.RepairProperties.readFrom(stream);
        } else {
            result.repair = null;
        }
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, size);
        StreamUtil.writeInt(stream, buildScore);
        StreamUtil.writeInt(stream, destroyScore);
        StreamUtil.writeBoolean(stream, canMove);
        StreamUtil.writeInt(stream, populationProvide);
        StreamUtil.writeInt(stream, populationUse);
        StreamUtil.writeInt(stream, maxHealth);
        StreamUtil.writeInt(stream, initialCost);
        StreamUtil.writeInt(stream, sightRange);
        StreamUtil.writeInt(stream, resourcePerHealth);
        if (build == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            build.writeTo(stream);
        }
        if (attack == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            attack.writeTo(stream);
        }
        if (repair == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            repair.writeTo(stream);
        }
    }
}
