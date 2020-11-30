package model

import util.StreamUtil

case class ColoredVertex(worldPos: Option[model.Vec2Float], screenOffset: model.Vec2Float, color: model.Color) {
    def writeTo(stream: java.io.OutputStream) {
        worldPos match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        screenOffset.writeTo(stream)
        color.writeTo(stream)
    }
}
object ColoredVertex {
    def readFrom(stream: java.io.InputStream): ColoredVertex = ColoredVertex(
        if (StreamUtil.readBoolean(stream)) Some(
            model.Vec2Float.readFrom(stream)
        ) else None
        ,
        model.Vec2Float.readFrom(stream)
        ,
        model.Color.readFrom(stream)
        )
}
