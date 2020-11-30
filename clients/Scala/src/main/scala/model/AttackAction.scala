package model

import util.StreamUtil

case class AttackAction(target: Option[Int], autoAttack: Option[model.AutoAttack]) {
    def writeTo(stream: java.io.OutputStream) {
        target match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                StreamUtil.writeInt(stream, value)
            }
        }
        autoAttack match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }
}
object AttackAction {
    def readFrom(stream: java.io.InputStream): AttackAction = AttackAction(
        if (StreamUtil.readBoolean(stream)) Some(
            StreamUtil.readInt(stream)
        ) else None
        ,
        if (StreamUtil.readBoolean(stream)) Some(
            model.AutoAttack.readFrom(stream)
        ) else None
        )
}
