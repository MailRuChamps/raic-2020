package model

import util.StreamUtil

case class Vec2Int(x: Int, y: Int) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, x)
        StreamUtil.writeInt(stream, y)
    }
}
object Vec2Int {
    def readFrom(stream: java.io.InputStream): Vec2Int = Vec2Int(
        StreamUtil.readInt(stream)
        ,
        StreamUtil.readInt(stream)
        )
}
