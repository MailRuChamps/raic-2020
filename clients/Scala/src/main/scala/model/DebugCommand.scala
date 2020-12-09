package model

import util.StreamUtil

sealed trait DebugCommand {
    def writeTo(stream: java.io.OutputStream)
}
object DebugCommand {
    case class Add(data: model.DebugData) extends DebugCommand {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Add.TAG)
            data.writeTo(stream)
        }
    }
    object Add {
        val TAG: Int = 0
        def readFrom(stream: java.io.InputStream): Add = Add(
            model.DebugData.readFrom(stream)
            )
    }

    case class Clear() extends DebugCommand {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Clear.TAG)
        }
    }
    object Clear {
        val TAG: Int = 1
        def readFrom(stream: java.io.InputStream): Clear = Clear(
            )
    }

    case class SetAutoFlush(enable: Boolean) extends DebugCommand {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, SetAutoFlush.TAG)
            StreamUtil.writeBoolean(stream, enable)
        }
    }
    object SetAutoFlush {
        val TAG: Int = 2
        def readFrom(stream: java.io.InputStream): SetAutoFlush = SetAutoFlush(
            StreamUtil.readBoolean(stream)
            )
    }

    case class Flush() extends DebugCommand {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Flush.TAG)
        }
    }
    object Flush {
        val TAG: Int = 3
        def readFrom(stream: java.io.InputStream): Flush = Flush(
            )
    }

    def readFrom(stream: java.io.InputStream): DebugCommand = {
        StreamUtil.readInt(stream) match {
            case Add.TAG => Add.readFrom(stream)
            case Clear.TAG => Clear.readFrom(stream)
            case SetAutoFlush.TAG => SetAutoFlush.readFrom(stream)
            case Flush.TAG => Flush.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}
