package model

import util.StreamUtil

sealed trait ClientMessage {
    def writeTo(stream: java.io.OutputStream)
}
object ClientMessage {
    case class DebugMessage(command: model.DebugCommand) extends ClientMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugMessage.TAG)
            command.writeTo(stream)
        }
    }
    object DebugMessage {
        val TAG: Int = 0
        def readFrom(stream: java.io.InputStream): DebugMessage = DebugMessage(
            model.DebugCommand.readFrom(stream)
            )
    }

    case class ActionMessage(action: model.Action) extends ClientMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, ActionMessage.TAG)
            action.writeTo(stream)
        }
    }
    object ActionMessage {
        val TAG: Int = 1
        def readFrom(stream: java.io.InputStream): ActionMessage = ActionMessage(
            model.Action.readFrom(stream)
            )
    }

    case class DebugUpdateDone() extends ClientMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugUpdateDone.TAG)
        }
    }
    object DebugUpdateDone {
        val TAG: Int = 2
        def readFrom(stream: java.io.InputStream): DebugUpdateDone = DebugUpdateDone(
            )
    }

    case class RequestDebugState() extends ClientMessage {
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, RequestDebugState.TAG)
        }
    }
    object RequestDebugState {
        val TAG: Int = 3
        def readFrom(stream: java.io.InputStream): RequestDebugState = RequestDebugState(
            )
    }

    def readFrom(stream: java.io.InputStream): ClientMessage = {
        StreamUtil.readInt(stream) match {
            case DebugMessage.TAG => DebugMessage.readFrom(stream)
            case ActionMessage.TAG => ActionMessage.readFrom(stream)
            case DebugUpdateDone.TAG => DebugUpdateDone.readFrom(stream)
            case RequestDebugState.TAG => RequestDebugState.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}
