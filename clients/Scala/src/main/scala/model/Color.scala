package model

import util.StreamUtil

case class Color(r: Float, g: Float, b: Float, a: Float) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeFloat(stream, r)
        StreamUtil.writeFloat(stream, g)
        StreamUtil.writeFloat(stream, b)
        StreamUtil.writeFloat(stream, a)
    }
}
object Color {
    def readFrom(stream: java.io.InputStream): Color = Color(
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readFloat(stream)
        ,
        StreamUtil.readFloat(stream)
        )
}
