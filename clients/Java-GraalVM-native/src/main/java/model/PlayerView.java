package model;

import util.StreamUtil;

public class PlayerView {
    private int myId;
    public int getMyId() { return myId; }
    public void setMyId(int myId) { this.myId = myId; }
    private int mapSize;
    public int getMapSize() { return mapSize; }
    public void setMapSize(int mapSize) { this.mapSize = mapSize; }
    private boolean fogOfWar;
    public boolean isFogOfWar() { return fogOfWar; }
    public void setFogOfWar(boolean fogOfWar) { this.fogOfWar = fogOfWar; }
    private java.util.Map<model.EntityType, model.EntityProperties> entityProperties;
    public java.util.Map<model.EntityType, model.EntityProperties> getEntityProperties() { return entityProperties; }
    public void setEntityProperties(java.util.Map<model.EntityType, model.EntityProperties> entityProperties) { this.entityProperties = entityProperties; }
    private int maxTickCount;
    public int getMaxTickCount() { return maxTickCount; }
    public void setMaxTickCount(int maxTickCount) { this.maxTickCount = maxTickCount; }
    private int maxPathfindNodes;
    public int getMaxPathfindNodes() { return maxPathfindNodes; }
    public void setMaxPathfindNodes(int maxPathfindNodes) { this.maxPathfindNodes = maxPathfindNodes; }
    private int currentTick;
    public int getCurrentTick() { return currentTick; }
    public void setCurrentTick(int currentTick) { this.currentTick = currentTick; }
    private model.Player[] players;
    public model.Player[] getPlayers() { return players; }
    public void setPlayers(model.Player[] players) { this.players = players; }
    private model.Entity[] entities;
    public model.Entity[] getEntities() { return entities; }
    public void setEntities(model.Entity[] entities) { this.entities = entities; }
    public PlayerView() {}
    public PlayerView(int myId, int mapSize, boolean fogOfWar, java.util.Map<model.EntityType, model.EntityProperties> entityProperties, int maxTickCount, int maxPathfindNodes, int currentTick, model.Player[] players, model.Entity[] entities) {
        this.myId = myId;
        this.mapSize = mapSize;
        this.fogOfWar = fogOfWar;
        this.entityProperties = entityProperties;
        this.maxTickCount = maxTickCount;
        this.maxPathfindNodes = maxPathfindNodes;
        this.currentTick = currentTick;
        this.players = players;
        this.entities = entities;
    }
    public static PlayerView readFrom(java.io.InputStream stream) throws java.io.IOException {
        PlayerView result = new PlayerView();
        result.myId = StreamUtil.readInt(stream);
        result.mapSize = StreamUtil.readInt(stream);
        result.fogOfWar = StreamUtil.readBoolean(stream);
        int entityPropertiesSize = StreamUtil.readInt(stream);
        result.entityProperties = new java.util.HashMap<>(entityPropertiesSize);
        for (int i = 0; i < entityPropertiesSize; i++) {
            model.EntityType entityPropertiesKey;
            switch (StreamUtil.readInt(stream)) {
            case 0:
                entityPropertiesKey = model.EntityType.WALL;
                break;
            case 1:
                entityPropertiesKey = model.EntityType.HOUSE;
                break;
            case 2:
                entityPropertiesKey = model.EntityType.BUILDER_BASE;
                break;
            case 3:
                entityPropertiesKey = model.EntityType.BUILDER_UNIT;
                break;
            case 4:
                entityPropertiesKey = model.EntityType.MELEE_BASE;
                break;
            case 5:
                entityPropertiesKey = model.EntityType.MELEE_UNIT;
                break;
            case 6:
                entityPropertiesKey = model.EntityType.RANGED_BASE;
                break;
            case 7:
                entityPropertiesKey = model.EntityType.RANGED_UNIT;
                break;
            case 8:
                entityPropertiesKey = model.EntityType.RESOURCE;
                break;
            case 9:
                entityPropertiesKey = model.EntityType.TURRET;
                break;
            default:
                throw new java.io.IOException("Unexpected tag value");
            }
            model.EntityProperties entityPropertiesValue;
            entityPropertiesValue = model.EntityProperties.readFrom(stream);
            result.entityProperties.put(entityPropertiesKey, entityPropertiesValue);
        }
        result.maxTickCount = StreamUtil.readInt(stream);
        result.maxPathfindNodes = StreamUtil.readInt(stream);
        result.currentTick = StreamUtil.readInt(stream);
        result.players = new model.Player[StreamUtil.readInt(stream)];
        for (int i = 0; i < result.players.length; i++) {
            result.players[i] = model.Player.readFrom(stream);
        }
        result.entities = new model.Entity[StreamUtil.readInt(stream)];
        for (int i = 0; i < result.entities.length; i++) {
            result.entities[i] = model.Entity.readFrom(stream);
        }
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, myId);
        StreamUtil.writeInt(stream, mapSize);
        StreamUtil.writeBoolean(stream, fogOfWar);
        StreamUtil.writeInt(stream, entityProperties.size());
        for (java.util.Map.Entry<model.EntityType, model.EntityProperties> entityPropertiesEntry : entityProperties.entrySet()) {
            model.EntityType entityPropertiesKey = entityPropertiesEntry.getKey();
            model.EntityProperties entityPropertiesValue = entityPropertiesEntry.getValue();
            StreamUtil.writeInt(stream, entityPropertiesKey.tag);
            entityPropertiesValue.writeTo(stream);
        }
        StreamUtil.writeInt(stream, maxTickCount);
        StreamUtil.writeInt(stream, maxPathfindNodes);
        StreamUtil.writeInt(stream, currentTick);
        StreamUtil.writeInt(stream, players.length);
        for (model.Player playersElement : players) {
            playersElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, entities.length);
        for (model.Entity entitiesElement : entities) {
            entitiesElement.writeTo(stream);
        }
    }
}
