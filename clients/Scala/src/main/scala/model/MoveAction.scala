package model

import util.StreamUtil

case class MoveAction(target: model.Vec2Int, findClosestPosition: Boolean, breakThrough: Boolean) {
    def writeTo(stream: java.io.OutputStream) {
        target.writeTo(stream)
        StreamUtil.writeBoolean(stream, findClosestPosition)
        StreamUtil.writeBoolean(stream, breakThrough)
    }
}
object MoveAction {
    def readFrom(stream: java.io.InputStream): MoveAction = MoveAction(
        model.Vec2Int.readFrom(stream)
        ,
        StreamUtil.readBoolean(stream)
        ,
        StreamUtil.readBoolean(stream)
        )
}
