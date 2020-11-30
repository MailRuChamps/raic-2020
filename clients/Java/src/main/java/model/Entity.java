package model;

import util.StreamUtil;

public class Entity {
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    private Integer playerId;
    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }
    private model.EntityType entityType;
    public model.EntityType getEntityType() { return entityType; }
    public void setEntityType(model.EntityType entityType) { this.entityType = entityType; }
    private model.Vec2Int position;
    public model.Vec2Int getPosition() { return position; }
    public void setPosition(model.Vec2Int position) { this.position = position; }
    private int health;
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    private boolean active;
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Entity() {}
    public Entity(int id, Integer playerId, model.EntityType entityType, model.Vec2Int position, int health, boolean active) {
        this.id = id;
        this.playerId = playerId;
        this.entityType = entityType;
        this.position = position;
        this.health = health;
        this.active = active;
    }
    public static Entity readFrom(java.io.InputStream stream) throws java.io.IOException {
        Entity result = new Entity();
        result.id = StreamUtil.readInt(stream);
        if (StreamUtil.readBoolean(stream)) {
            result.playerId = StreamUtil.readInt(stream);
        } else {
            result.playerId = null;
        }
        switch (StreamUtil.readInt(stream)) {
        case 0:
            result.entityType = model.EntityType.WALL;
            break;
        case 1:
            result.entityType = model.EntityType.HOUSE;
            break;
        case 2:
            result.entityType = model.EntityType.BUILDER_BASE;
            break;
        case 3:
            result.entityType = model.EntityType.BUILDER_UNIT;
            break;
        case 4:
            result.entityType = model.EntityType.MELEE_BASE;
            break;
        case 5:
            result.entityType = model.EntityType.MELEE_UNIT;
            break;
        case 6:
            result.entityType = model.EntityType.RANGED_BASE;
            break;
        case 7:
            result.entityType = model.EntityType.RANGED_UNIT;
            break;
        case 8:
            result.entityType = model.EntityType.RESOURCE;
            break;
        case 9:
            result.entityType = model.EntityType.TURRET;
            break;
        default:
            throw new java.io.IOException("Unexpected tag value");
        }
        result.position = model.Vec2Int.readFrom(stream);
        result.health = StreamUtil.readInt(stream);
        result.active = StreamUtil.readBoolean(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, id);
        if (playerId == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, playerId);
        }
        StreamUtil.writeInt(stream, entityType.tag);
        position.writeTo(stream);
        StreamUtil.writeInt(stream, health);
        StreamUtil.writeBoolean(stream, active);
    }
}
