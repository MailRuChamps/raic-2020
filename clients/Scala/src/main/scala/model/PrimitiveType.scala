package model

import util.StreamUtil

sealed abstract class PrimitiveType (val tag: Int) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, tag)
    }
}

object PrimitiveType {
    case object LINES extends PrimitiveType(0)
    case object TRIANGLES extends PrimitiveType(1)
    def readFrom(stream: java.io.InputStream): PrimitiveType = StreamUtil.readInt(stream) match {
        case 0 => LINES
        case 1 => TRIANGLES
        case _ => throw new java.io.IOException("Unexpected tag value")
    }
}
