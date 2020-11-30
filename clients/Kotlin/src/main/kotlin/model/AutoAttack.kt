package model

import util.StreamUtil

class AutoAttack {
    var pathfindRange: Int = 0
    lateinit var validTargets: Array<model.EntityType>
    constructor() {}
    constructor(pathfindRange: Int, validTargets: Array<model.EntityType>) {
        this.pathfindRange = pathfindRange
        this.validTargets = validTargets
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): AutoAttack {
            val result = AutoAttack()
            result.pathfindRange = StreamUtil.readInt(stream)
            result.validTargets = Array(StreamUtil.readInt(stream), {
                var validTargetsValue: model.EntityType
                when (StreamUtil.readInt(stream)) {
                0 ->validTargetsValue = model.EntityType.WALL
                1 ->validTargetsValue = model.EntityType.HOUSE
                2 ->validTargetsValue = model.EntityType.BUILDER_BASE
                3 ->validTargetsValue = model.EntityType.BUILDER_UNIT
                4 ->validTargetsValue = model.EntityType.MELEE_BASE
                5 ->validTargetsValue = model.EntityType.MELEE_UNIT
                6 ->validTargetsValue = model.EntityType.RANGED_BASE
                7 ->validTargetsValue = model.EntityType.RANGED_UNIT
                8 ->validTargetsValue = model.EntityType.RESOURCE
                9 ->validTargetsValue = model.EntityType.TURRET
                else -> throw java.io.IOException("Unexpected tag value")
                }
                validTargetsValue
            })
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, pathfindRange)
        StreamUtil.writeInt(stream, validTargets.size)
        for (validTargetsElement in validTargets) {
            StreamUtil.writeInt(stream, validTargetsElement.tag)
        }
    }
}
