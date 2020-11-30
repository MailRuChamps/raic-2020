package model

import util.StreamUtil

class AttackAction {
    var target: Int? = null
    var autoAttack: model.AutoAttack? = null
    constructor() {}
    constructor(target: Int?, autoAttack: model.AutoAttack?) {
        this.target = target
        this.autoAttack = autoAttack
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): AttackAction {
            val result = AttackAction()
            if (StreamUtil.readBoolean(stream)) {
                result.target = StreamUtil.readInt(stream)
            } else {
                result.target = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.autoAttack = model.AutoAttack.readFrom(stream)
            } else {
                result.autoAttack = null
            }
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        val target = target;
        if (target == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, target)
        }
        val autoAttack = autoAttack;
        if (autoAttack == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            autoAttack.writeTo(stream)
        }
    }
}
