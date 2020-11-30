package model

import util.StreamUtil

case class EntityAction(moveAction: Option[model.MoveAction], buildAction: Option[model.BuildAction], attackAction: Option[model.AttackAction], repairAction: Option[model.RepairAction]) {
    def writeTo(stream: java.io.OutputStream) {
        moveAction match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        buildAction match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        attackAction match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        repairAction match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }
}
object EntityAction {
    def readFrom(stream: java.io.InputStream): EntityAction = EntityAction(
        if (StreamUtil.readBoolean(stream)) Some(
            model.MoveAction.readFrom(stream)
        ) else None
        ,
        if (StreamUtil.readBoolean(stream)) Some(
            model.BuildAction.readFrom(stream)
        ) else None
        ,
        if (StreamUtil.readBoolean(stream)) Some(
            model.AttackAction.readFrom(stream)
        ) else None
        ,
        if (StreamUtil.readBoolean(stream)) Some(
            model.RepairAction.readFrom(stream)
        ) else None
        )
}
