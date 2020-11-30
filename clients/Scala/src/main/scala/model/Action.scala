package model

import util.StreamUtil

case class Action(entityActions: Map[Int, model.EntityAction]) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, entityActions.size)
        entityActions.foreach { case (key, value) =>
            StreamUtil.writeInt(stream, key)
            value.writeTo(stream)
        }
    }
}
object Action {
    def readFrom(stream: java.io.InputStream): Action = Action(
        (0 until StreamUtil.readInt(stream)).map { _ => (
            StreamUtil.readInt(stream)
            ,
            model.EntityAction.readFrom(stream)
        )}.toMap
        )
}
