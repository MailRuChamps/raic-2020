package model

import util.StreamUtil

class RepairProperties {
    lateinit var validTargets: Array<model.EntityType>
    var power: Int = 0
    constructor() {}
    constructor(validTargets: Array<model.EntityType>, power: Int) {
        this.validTargets = validTargets
        this.power = power
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): RepairProperties {
            val result = RepairProperties()
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
            result.power = StreamUtil.readInt(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, validTargets.size)
        for (validTargetsElement in validTargets) {
            StreamUtil.writeInt(stream, validTargetsElement.tag)
        }
        StreamUtil.writeInt(stream, power)
    }
}
