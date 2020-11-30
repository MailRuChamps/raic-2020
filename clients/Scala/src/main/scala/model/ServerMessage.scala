package model

import util.StreamUtil

sealed trait ServerMessage {
    def writeTo(stream: java.io.OutputStream)
}
object ServerMessage {
    case class GetAction(playerView: model.PlayerView, debugAvailable: Boolean) extends ServerMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, GetAction.TAG)
            playerView.writeTo(stream)
            StreamUtil.writeBoolean(stream, debugAvailable)
        }
    }
    object GetAction {
        val TAG: Int = 0
        def readFrom(stream: java.io.InputStream): GetAction = GetAction(
            model.PlayerView.readFrom(stream)
            ,
            StreamUtil.readBoolean(stream)
            )
    }

    case class Finish() extends ServerMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Finish.TAG)
        }
    }
    object Finish {
        val TAG: Int = 1
        def readFrom(stream: java.io.InputStream): Finish = Finish(
            )
    }

    case class DebugUpdate(playerView: model.PlayerView) extends ServerMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugUpdate.TAG)
            playerView.writeTo(stream)
        }
    }
    object DebugUpdate {
        val TAG: Int = 2
        def readFrom(stream: java.io.InputStream): DebugUpdate = DebugUpdate(
            model.PlayerView.readFrom(stream)
            )
    }

    def readFrom(stream: java.io.InputStream): ServerMessage = {
        StreamUtil.readInt(stream) match {
            case GetAction.TAG => GetAction.readFrom(stream)
            case Finish.TAG => Finish.readFrom(stream)
            case DebugUpdate.TAG => DebugUpdate.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}
