package model

import util.StreamUtil

class BuildProperties {
    lateinit var options: Array<model.EntityType>
    var initHealth: Int? = null
    constructor() {}
    constructor(options: Array<model.EntityType>, initHealth: Int?) {
        this.options = options
        this.initHealth = initHealth
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): BuildProperties {
            val result = BuildProperties()
            result.options = Array(StreamUtil.readInt(stream), {
                var optionsValue: model.EntityType
                when (StreamUtil.readInt(stream)) {
                0 ->optionsValue = model.EntityType.WALL
                1 ->optionsValue = model.EntityType.HOUSE
                2 ->optionsValue = model.EntityType.BUILDER_BASE
                3 ->optionsValue = model.EntityType.BUILDER_UNIT
                4 ->optionsValue = model.EntityType.MELEE_BASE
                5 ->optionsValue = model.EntityType.MELEE_UNIT
                6 ->optionsValue = model.EntityType.RANGED_BASE
                7 ->optionsValue = model.EntityType.RANGED_UNIT
                8 ->optionsValue = model.EntityType.RESOURCE
                9 ->optionsValue = model.EntityType.TURRET
                else -> throw java.io.IOException("Unexpected tag value")
                }
                optionsValue
            })
            if (StreamUtil.readBoolean(stream)) {
                result.initHealth = StreamUtil.readInt(stream)
            } else {
                result.initHealth = null
            }
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, options.size)
        for (optionsElement in options) {
            StreamUtil.writeInt(stream, optionsElement.tag)
        }
        val initHealth = initHealth;
        if (initHealth == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, initHealth)
        }
    }
}
