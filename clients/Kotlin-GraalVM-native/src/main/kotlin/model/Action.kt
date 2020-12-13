package model

import util.StreamUtil

class Action {
    lateinit var entityActions: MutableMap<Int, model.EntityAction>
    constructor() {}
    constructor(entityActions: MutableMap<Int, model.EntityAction>) {
        this.entityActions = entityActions
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Action {
            val result = Action()
            val entityActionsSize = StreamUtil.readInt(stream)
            result.entityActions = mutableMapOf()
            for (i in 0 until entityActionsSize) {
                var entityActionsKey: Int
                entityActionsKey = StreamUtil.readInt(stream)
                var entityActionsValue: model.EntityAction
                entityActionsValue = model.EntityAction.readFrom(stream)
                result.entityActions.put(entityActionsKey, entityActionsValue)
            }
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, entityActions.size)
        for (entityActionsEntry in entityActions) {
            StreamUtil.writeInt(stream, entityActionsEntry.key)
            entityActionsEntry.value.writeTo(stream)
        }
    }
}
