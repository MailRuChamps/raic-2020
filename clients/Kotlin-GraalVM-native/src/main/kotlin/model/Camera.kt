package model

import util.StreamUtil

class Camera {
    lateinit var center: model.Vec2Float
    var rotation: Float = 0.0f
    var attack: Float = 0.0f
    var distance: Float = 0.0f
    var perspective: Boolean = false
    constructor() {}
    constructor(center: model.Vec2Float, rotation: Float, attack: Float, distance: Float, perspective: Boolean) {
        this.center = center
        this.rotation = rotation
        this.attack = attack
        this.distance = distance
        this.perspective = perspective
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Camera {
            val result = Camera()
            result.center = model.Vec2Float.readFrom(stream)
            result.rotation = StreamUtil.readFloat(stream)
            result.attack = StreamUtil.readFloat(stream)
            result.distance = StreamUtil.readFloat(stream)
            result.perspective = StreamUtil.readBoolean(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        center.writeTo(stream)
        StreamUtil.writeFloat(stream, rotation)
        StreamUtil.writeFloat(stream, attack)
        StreamUtil.writeFloat(stream, distance)
        StreamUtil.writeBoolean(stream, perspective)
    }
}
