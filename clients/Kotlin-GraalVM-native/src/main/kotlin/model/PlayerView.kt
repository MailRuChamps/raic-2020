package model

import util.StreamUtil

class PlayerView {
    var myId: Int = 0
    var mapSize: Int = 0
    var fogOfWar: Boolean = false
    lateinit var entityProperties: MutableMap<model.EntityType, model.EntityProperties>
    var maxTickCount: Int = 0
    var maxPathfindNodes: Int = 0
    var currentTick: Int = 0
    lateinit var players: Array<model.Player>
    lateinit var entities: Array<model.Entity>
    constructor() {}
    constructor(myId: Int, mapSize: Int, fogOfWar: Boolean, entityProperties: MutableMap<model.EntityType, model.EntityProperties>, maxTickCount: Int, maxPathfindNodes: Int, currentTick: Int, players: Array<model.Player>, entities: Array<model.Entity>) {
        this.myId = myId
        this.mapSize = mapSize
        this.fogOfWar = fogOfWar
        this.entityProperties = entityProperties
        this.maxTickCount = maxTickCount
        this.maxPathfindNodes = maxPathfindNodes
        this.currentTick = currentTick
        this.players = players
        this.entities = entities
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): PlayerView {
            val result = PlayerView()
            result.myId = StreamUtil.readInt(stream)
            result.mapSize = StreamUtil.readInt(stream)
            result.fogOfWar = StreamUtil.readBoolean(stream)
            val entityPropertiesSize = StreamUtil.readInt(stream)
            result.entityProperties = mutableMapOf()
            for (i in 0 until entityPropertiesSize) {
                var entityPropertiesKey: model.EntityType
                when (StreamUtil.readInt(stream)) {
                0 ->entityPropertiesKey = model.EntityType.WALL
                1 ->entityPropertiesKey = model.EntityType.HOUSE
                2 ->entityPropertiesKey = model.EntityType.BUILDER_BASE
                3 ->entityPropertiesKey = model.EntityType.BUILDER_UNIT
                4 ->entityPropertiesKey = model.EntityType.MELEE_BASE
                5 ->entityPropertiesKey = model.EntityType.MELEE_UNIT
                6 ->entityPropertiesKey = model.EntityType.RANGED_BASE
                7 ->entityPropertiesKey = model.EntityType.RANGED_UNIT
                8 ->entityPropertiesKey = model.EntityType.RESOURCE
                9 ->entityPropertiesKey = model.EntityType.TURRET
                else -> throw java.io.IOException("Unexpected tag value")
                }
                var entityPropertiesValue: model.EntityProperties
                entityPropertiesValue = model.EntityProperties.readFrom(stream)
                result.entityProperties.put(entityPropertiesKey, entityPropertiesValue)
            }
            result.maxTickCount = StreamUtil.readInt(stream)
            result.maxPathfindNodes = StreamUtil.readInt(stream)
            result.currentTick = StreamUtil.readInt(stream)
            result.players = Array(StreamUtil.readInt(stream), {
                var playersValue: model.Player
                playersValue = model.Player.readFrom(stream)
                playersValue
            })
            result.entities = Array(StreamUtil.readInt(stream), {
                var entitiesValue: model.Entity
                entitiesValue = model.Entity.readFrom(stream)
                entitiesValue
            })
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, myId)
        StreamUtil.writeInt(stream, mapSize)
        StreamUtil.writeBoolean(stream, fogOfWar)
        StreamUtil.writeInt(stream, entityProperties.size)
        for (entityPropertiesEntry in entityProperties) {
            StreamUtil.writeInt(stream, entityPropertiesEntry.key.tag)
            entityPropertiesEntry.value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, maxTickCount)
        StreamUtil.writeInt(stream, maxPathfindNodes)
        StreamUtil.writeInt(stream, currentTick)
        StreamUtil.writeInt(stream, players.size)
        for (playersElement in players) {
            playersElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, entities.size)
        for (entitiesElement in entities) {
            entitiesElement.writeTo(stream)
        }
    }
}
