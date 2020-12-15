import {EntityProperties} from './EntityProperties';
import {Player} from './Player';
import {Entity} from './Entity';
import {EntityType} from './EntityType';
import {Stream} from './Stream';

export class PlayerView {
    constructor(
        public myId: number,
        public mapSize: number,
        public fogOfWar: boolean,
        public entityProperties: Map<EntityType, EntityProperties>,
        public maxTickCount: number,
        public maxPathfindNodes: number,
        public currentTick: number,
        public players: Player[],
        public entities: Entity[],
    ) {}

    static async readFrom(stream: Stream) {
        const myId = await stream.readInt();
        const mapSize = await stream.readInt();
        const fogOfWar = await stream.readBool();
        const  entityProperties = new Map<EntityType, EntityProperties>();

        for (let i = await stream.readInt(); i > 0; i--) {
            const entityPropertiesKey = await stream.readInt();
            const  entityPropertiesValue = await EntityProperties.readFrom(stream);

            entityProperties.set(entityPropertiesKey, entityPropertiesValue);
        }

        const maxTickCount = await stream.readInt();
        const maxPathfindNodes = await stream.readInt();
        const currentTick = await stream.readInt();
        const players = [];

        for (let i = await stream.readInt(); i > 0; i--) {
            let playersElement;
            playersElement = await Player.readFrom(stream);
            players.push(playersElement);
        }

        const entities = [];

        for (let i = await stream.readInt(); i > 0; i--) {
            entities.push(await Entity.readFrom(stream));
        }

        return new PlayerView(myId, mapSize, fogOfWar, entityProperties, maxTickCount, maxPathfindNodes, currentTick, players, entities);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(this.myId);
        await stream.writeInt(this.mapSize);
        await stream.writeBool(this.fogOfWar);
        await stream.writeInt(this.entityProperties.size);

        for (let [entityType, entityProperties] of this.entityProperties) {
            await stream.writeInt(entityType);
            await entityProperties.writeTo(stream);
        }

        await stream.writeInt(this.maxTickCount);
        await stream.writeInt(this.maxPathfindNodes);
        await stream.writeInt(this.currentTick);
        await stream.writeInt(this.players.length);

        for (let playersElement of this.players) {
            await playersElement.writeTo(stream);
        }

        await stream.writeInt(this.entities.length);
        for (let entitiesElement of this.entities) {
            await entitiesElement.writeTo(stream);
        }
    }
}
