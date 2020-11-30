package model

import util.StreamUtil

case class Camera(center: model.Vec2Float, rotation: Float, attack: Float, distance: Float, perspective: Boolean) {
    def writeTo(stream: java.io.OutputStream) {
        center.writeTo(stream)
        StreamUtil.writeFloat(stream, rotation)
        StreamUtil.writeFloat(stream, attack)
        StreamUtil.writeFloat(stream, distance)
        StreamUtil.writeBoolean(stream, perspective)
    }
}
object Camera {
    def readFrom(stream: java.io.InputStream): Camera = Camera(
        model.Vec2Float.readFrom(stream)
        ,
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readBoolean(stream)
        )
}
