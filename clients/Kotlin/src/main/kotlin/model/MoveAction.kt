package model

import util.StreamUtil

class MoveAction {
    lateinit var target: model.Vec2Int
    var findClosestPosition: Boolean = false
    var breakThrough: Boolean = false
    constructor() {}
    constructor(target: model.Vec2Int, findClosestPosition: Boolean, breakThrough: Boolean) {
        this.target = target
        this.findClosestPosition = findClosestPosition
        this.breakThrough = breakThrough
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): MoveAction {
            val result = MoveAction()
            result.target = model.Vec2Int.readFrom(stream)
            result.findClosestPosition = StreamUtil.readBoolean(stream)
            result.breakThrough = StreamUtil.readBoolean(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        target.writeTo(stream)
        StreamUtil.writeBoolean(stream, findClosestPosition)
        StreamUtil.writeBoolean(stream, breakThrough)
    }
}
