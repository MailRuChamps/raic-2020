package model

import util.StreamUtil

case class AttackProperties(attackRange: Int, damage: Int, collectResource: Boolean) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, attackRange)
        StreamUtil.writeInt(stream, damage)
        StreamUtil.writeBoolean(stream, collectResource)
    }
}
object AttackProperties {
    def readFrom(stream: java.io.InputStream): AttackProperties = AttackProperties(
        StreamUtil.readInt(stream)
        ,
        StreamUtil.readInt(stream)
        ,
        StreamUtil.readBoolean(stream)
        )
}
