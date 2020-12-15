package model

import util.StreamUtil

class EntityAction {
    var moveAction: model.MoveAction? = null
    var buildAction: model.BuildAction? = null
    var attackAction: model.AttackAction? = null
    var repairAction: model.RepairAction? = null
    constructor() {}
    constructor(moveAction: model.MoveAction?, buildAction: model.BuildAction?, attackAction: model.AttackAction?, repairAction: model.RepairAction?) {
        this.moveAction = moveAction
        this.buildAction = buildAction
        this.attackAction = attackAction
        this.repairAction = repairAction
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): EntityAction {
            val result = EntityAction()
            if (StreamUtil.readBoolean(stream)) {
                result.moveAction = model.MoveAction.readFrom(stream)
            } else {
                result.moveAction = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.buildAction = model.BuildAction.readFrom(stream)
            } else {
                result.buildAction = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.attackAction = model.AttackAction.readFrom(stream)
            } else {
                result.attackAction = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.repairAction = model.RepairAction.readFrom(stream)
            } else {
                result.repairAction = null
            }
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        val moveAction = moveAction;
        if (moveAction == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            moveAction.writeTo(stream)
        }
        val buildAction = buildAction;
        if (buildAction == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            buildAction.writeTo(stream)
        }
        val attackAction = attackAction;
        if (attackAction == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            attackAction.writeTo(stream)
        }
        val repairAction = repairAction;
        if (repairAction == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            repairAction.writeTo(stream)
        }
    }
}
