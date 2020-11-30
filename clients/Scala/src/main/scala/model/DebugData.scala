package model

import util.StreamUtil

sealed trait DebugData {
    def writeTo(stream: java.io.OutputStream)
}
object DebugData {
    case class Log(text: String) extends DebugData {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Log.TAG)
            StreamUtil.writeString(stream, text)
        }
    }
    object Log {
        val TAG: Int = 0
        def readFrom(stream: java.io.InputStream): Log = Log(
            StreamUtil.readString(stream)
            )
    }

    case class Primitives(vertices: Seq[model.ColoredVertex], primitiveType: model.PrimitiveType) extends DebugData {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Primitives.TAG)
            StreamUtil.writeInt(stream, vertices.length)
            vertices.foreach { value =>
                value.writeTo(stream)
            }
            primitiveType.writeTo(stream)
        }
    }
    object Primitives {
        val TAG: Int = 1
        def readFrom(stream: java.io.InputStream): Primitives = Primitives(
            (0 until StreamUtil.readInt(stream)).map { _ =>
                model.ColoredVertex.readFrom(stream)
            }
            ,
            model.PrimitiveType.readFrom(stream)
            )
    }

    case class PlacedText(vertex: model.ColoredVertex, text: String, alignment: Float, size: Float) extends DebugData {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, PlacedText.TAG)
            vertex.writeTo(stream)
            StreamUtil.writeString(stream, text)
            StreamUtil.writeFloat(stream, alignment)
            StreamUtil.writeFloat(stream, size)
        }
    }
    object PlacedText {
        val TAG: Int = 2
        def readFrom(stream: java.io.InputStream): PlacedText = PlacedText(
            model.ColoredVertex.readFrom(stream)
            ,
            StreamUtil.readString(stream)
            ,
            StreamUtil.readFloat(stream)
            ,
            StreamUtil.readFloat(stream)
            )
    }

    def readFrom(stream: java.io.InputStream): DebugData = {
        StreamUtil.readInt(stream) match {
            case Log.TAG => Log.readFrom(stream)
            case Primitives.TAG => Primitives.readFrom(stream)
            case PlacedText.TAG => PlacedText.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}
