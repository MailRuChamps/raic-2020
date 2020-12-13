package model

import util.StreamUtil

abstract class DebugCommand {
    @Throws(java.io.IOException::class)
    abstract fun writeTo(stream: java.io.OutputStream)
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): DebugCommand {
            when (StreamUtil.readInt(stream)) {
                Add.TAG -> return Add.readFrom(stream)
                Clear.TAG -> return Clear.readFrom(stream)
                SetAutoFlush.TAG -> return SetAutoFlush.readFrom(stream)
                Flush.TAG -> return Flush.readFrom(stream)
                else -> throw java.io.IOException("Unexpected tag value")
            }
        }
    }

    class Add : DebugCommand {
        lateinit var data: model.DebugData
        constructor() {}
        constructor(data: model.DebugData) {
            this.data = data
        }
        companion object {
            val TAG = 0
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): Add {
                val result = Add()
                result.data = model.DebugData.readFrom(stream)
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
            data.writeTo(stream)
        }
    }

    class Clear : DebugCommand {
        constructor() {}
        companion object {
            val TAG = 1
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): Clear {
                val result = Clear()
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
        }
    }

    class SetAutoFlush : DebugCommand {
        var enable: Boolean = false
        constructor() {}
        constructor(enable: Boolean) {
            this.enable = enable
        }
        companion object {
            val TAG = 2
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): SetAutoFlush {
                val result = SetAutoFlush()
                result.enable = StreamUtil.readBoolean(stream)
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
            StreamUtil.writeBoolean(stream, enable)
        }
    }

    class Flush : DebugCommand {
        constructor() {}
        companion object {
            val TAG = 3
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): Flush {
                val result = Flush()
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
        }
    }
}
