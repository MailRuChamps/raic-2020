package model

import util.StreamUtil

class BuildAction {
    lateinit var entityType: model.EntityType
    lateinit var position: model.Vec2Int
    constructor() {}
    constructor(entityType: model.EntityType, position: model.Vec2Int) {
        this.entityType = entityType
        this.position = position
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): BuildAction {
            val result = BuildAction()
            when (StreamUtil.readInt(stream)) {
            0 ->result.entityType = model.EntityType.WALL
            1 ->result.entityType = model.EntityType.HOUSE
            2 ->result.entityType = model.EntityType.BUILDER_BASE
            3 ->result.entityType = model.EntityType.BUILDER_UNIT
            4 ->result.entityType = model.EntityType.MELEE_BASE
            5 ->result.entityType = model.EntityType.MELEE_UNIT
            6 ->result.entityType = model.EntityType.RANGED_BASE
            7 ->result.entityType = model.EntityType.RANGED_UNIT
            8 ->result.entityType = model.EntityType.RESOURCE
            9 ->result.entityType = model.EntityType.TURRET
            else -> throw java.io.IOException("Unexpected tag value")
            }
            result.position = model.Vec2Int.readFrom(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, entityType.tag)
        position.writeTo(stream)
    }
}
