package model

import util.StreamUtil

class AttackProperties {
    var attackRange: Int = 0
    var damage: Int = 0
    var collectResource: Boolean = false
    constructor() {}
    constructor(attackRange: Int, damage: Int, collectResource: Boolean) {
        this.attackRange = attackRange
        this.damage = damage
        this.collectResource = collectResource
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): AttackProperties {
            val result = AttackProperties()
            result.attackRange = StreamUtil.readInt(stream)
            result.damage = StreamUtil.readInt(stream)
            result.collectResource = StreamUtil.readBoolean(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, attackRange)
        StreamUtil.writeInt(stream, damage)
        StreamUtil.writeBoolean(stream, collectResource)
    }
}
