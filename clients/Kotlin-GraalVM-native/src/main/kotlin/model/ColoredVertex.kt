package model

import util.StreamUtil

class ColoredVertex {
    var worldPos: model.Vec2Float? = null
    lateinit var screenOffset: model.Vec2Float
    lateinit var color: model.Color
    constructor() {}
    constructor(worldPos: model.Vec2Float?, screenOffset: model.Vec2Float, color: model.Color) {
        this.worldPos = worldPos
        this.screenOffset = screenOffset
        this.color = color
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): ColoredVertex {
            val result = ColoredVertex()
            if (StreamUtil.readBoolean(stream)) {
                result.worldPos = model.Vec2Float.readFrom(stream)
            } else {
                result.worldPos = null
            }
            result.screenOffset = model.Vec2Float.readFrom(stream)
            result.color = model.Color.readFrom(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        val worldPos = worldPos;
        if (worldPos == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            worldPos.writeTo(stream)
        }
        screenOffset.writeTo(stream)
        color.writeTo(stream)
    }
}
