package model

import util.StreamUtil

class DebugState {
    lateinit var windowSize: model.Vec2Int
    lateinit var mousePosWindow: model.Vec2Float
    lateinit var mousePosWorld: model.Vec2Float
    lateinit var pressedKeys: Array<String>
    lateinit var camera: model.Camera
    var playerIndex: Int = 0
    constructor() {}
    constructor(windowSize: model.Vec2Int, mousePosWindow: model.Vec2Float, mousePosWorld: model.Vec2Float, pressedKeys: Array<String>, camera: model.Camera, playerIndex: Int) {
        this.windowSize = windowSize
        this.mousePosWindow = mousePosWindow
        this.mousePosWorld = mousePosWorld
        this.pressedKeys = pressedKeys
        this.camera = camera
        this.playerIndex = playerIndex
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): DebugState {
            val result = DebugState()
            result.windowSize = model.Vec2Int.readFrom(stream)
            result.mousePosWindow = model.Vec2Float.readFrom(stream)
            result.mousePosWorld = model.Vec2Float.readFrom(stream)
            result.pressedKeys = Array(StreamUtil.readInt(stream), {
                var pressedKeysValue: String
                pressedKeysValue = StreamUtil.readString(stream)
                pressedKeysValue
            })
            result.camera = model.Camera.readFrom(stream)
            result.playerIndex = StreamUtil.readInt(stream)
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        windowSize.writeTo(stream)
        mousePosWindow.writeTo(stream)
        mousePosWorld.writeTo(stream)
        StreamUtil.writeInt(stream, pressedKeys.size)
        for (pressedKeysElement in pressedKeys) {
            StreamUtil.writeString(stream, pressedKeysElement)
        }
        camera.writeTo(stream)
        StreamUtil.writeInt(stream, playerIndex)
    }
}
