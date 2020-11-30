const EntityProperties = require('./entity-properties');
const Player = require('./player');
const Entity = require('./entity');
class PlayerView {
    constructor(myId, mapSize, fogOfWar, entityProperties, maxTickCount, maxPathfindNodes, currentTick, players, entities) {
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
    static async readFrom(stream) {
        let myId;
        myId = await stream.readInt();
        let mapSize;
        mapSize = await stream.readInt();
        let fogOfWar;
        fogOfWar = await stream.readBool();
        let entityProperties;
        entityProperties = new Map();
        for (let i = await stream.readInt(); i > 0; i--) {
            let entityPropertiesKey;
            let entityPropertiesValue;
            entityPropertiesKey = await stream.readInt();
            entityPropertiesValue = await EntityProperties.readFrom(stream);
            entityProperties.set(entityPropertiesKey, entityPropertiesValue);
        }
        let maxTickCount;
        maxTickCount = await stream.readInt();
        let maxPathfindNodes;
        maxPathfindNodes = await stream.readInt();
        let currentTick;
        currentTick = await stream.readInt();
        let players;
        players = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let playersElement;
            playersElement = await Player.readFrom(stream);
            players.push(playersElement);
        }
        let entities;
        entities = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let entitiesElement;
            entitiesElement = await Entity.readFrom(stream);
            entities.push(entitiesElement);
        }
        return new PlayerView(myId, mapSize, fogOfWar, entityProperties, maxTickCount, maxPathfindNodes, currentTick, players, entities);
    }
    async writeTo(stream) {
        let myId = this.myId;
        await stream.writeInt(myId);
        let mapSize = this.mapSize;
        await stream.writeInt(mapSize);
        let fogOfWar = this.fogOfWar;
        await stream.writeBool(fogOfWar);
        let entityProperties = this.entityProperties;
        await stream.writeInt(entityProperties.size);
        for (let entityPropertiesEntry of entityProperties) {
            let entityPropertiesKey = entityPropertiesEntry[0];
            let entityPropertiesValue = entityPropertiesEntry[1];
            await stream.writeInt(entityPropertiesKey);
            await entityPropertiesValue.writeTo(stream);
        }
        let maxTickCount = this.maxTickCount;
        await stream.writeInt(maxTickCount);
        let maxPathfindNodes = this.maxPathfindNodes;
        await stream.writeInt(maxPathfindNodes);
        let currentTick = this.currentTick;
        await stream.writeInt(currentTick);
        let players = this.players;
        await stream.writeInt(players.length);
        for (let playersElement of players) {
            await playersElement.writeTo(stream);
        }
        let entities = this.entities;
        await stream.writeInt(entities.length);
        for (let entitiesElement of entities) {
            await entitiesElement.writeTo(stream);
        }
    }
}
module.exports = PlayerView
