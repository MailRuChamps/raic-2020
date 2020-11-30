package model

import util.StreamUtil

case class DebugState(windowSize: model.Vec2Int, mousePosWindow: model.Vec2Float, mousePosWorld: model.Vec2Float, pressedKeys: Seq[String], camera: model.Camera, playerIndex: Int) {
    def writeTo(stream: java.io.OutputStream) {
        windowSize.writeTo(stream)
        mousePosWindow.writeTo(stream)
        mousePosWorld.writeTo(stream)
        StreamUtil.writeInt(stream, pressedKeys.length)
        pressedKeys.foreach { value =>
            StreamUtil.writeString(stream, value)
        }
        camera.writeTo(stream)
        StreamUtil.writeInt(stream, playerIndex)
    }
}
object DebugState {
    def readFrom(stream: java.io.InputStream): DebugState = DebugState(
        model.Vec2Int.readFrom(stream)
        ,
        model.Vec2Float.readFrom(stream)
        ,
        model.Vec2Float.readFrom(stream)
        ,
        (0 until StreamUtil.readInt(stream)).map { _ =>
            StreamUtil.readString(stream)
        }
        ,
        model.Camera.readFrom(stream)
        ,
        StreamUtil.readInt(stream)
        )
}
