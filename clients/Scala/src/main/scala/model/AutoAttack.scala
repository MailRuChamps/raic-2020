package model

import util.StreamUtil

case class AutoAttack(pathfindRange: Int, validTargets: Seq[model.EntityType]) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, pathfindRange)
        StreamUtil.writeInt(stream, validTargets.length)
        validTargets.foreach { value =>
            value.writeTo(stream)
        }
    }
}
object AutoAttack {
    def readFrom(stream: java.io.InputStream): AutoAttack = AutoAttack(
        StreamUtil.readInt(stream)
        ,
        (0 until StreamUtil.readInt(stream)).map { _ =>
            model.EntityType.readFrom(stream)
        }
        )
}
