package model

import util.StreamUtil

abstract class DebugData {
    @Throws(java.io.IOException::class)
    abstract fun writeTo(stream: java.io.OutputStream)
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): DebugData {
            when (StreamUtil.readInt(stream)) {
                Log.TAG -> return Log.readFrom(stream)
                Primitives.TAG -> return Primitives.readFrom(stream)
                PlacedText.TAG -> return PlacedText.readFrom(stream)
                else -> throw java.io.IOException("Unexpected tag value")
            }
        }
    }

    class Log : DebugData {
        lateinit var text: String
        constructor() {}
        constructor(text: String) {
            this.text = text
        }
        companion object {
            val TAG = 0
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): Log {
                val result = Log()
                result.text = StreamUtil.readString(stream)
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
            StreamUtil.writeString(stream, text)
        }
    }

    class Primitives : DebugData {
        lateinit var vertices: Array<model.ColoredVertex>
        lateinit var primitiveType: model.PrimitiveType
        constructor() {}
        constructor(vertices: Array<model.ColoredVertex>, primitiveType: model.PrimitiveType) {
            this.vertices = vertices
            this.primitiveType = primitiveType
        }
        companion object {
            val TAG = 1
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): Primitives {
                val result = Primitives()
                result.vertices = Array(StreamUtil.readInt(stream), {
                    var verticesValue: model.ColoredVertex
                    verticesValue = model.ColoredVertex.readFrom(stream)
                    verticesValue
                })
                when (StreamUtil.readInt(stream)) {
                0 ->result.primitiveType = model.PrimitiveType.LINES
                1 ->result.primitiveType = model.PrimitiveType.TRIANGLES
                else -> throw java.io.IOException("Unexpected tag value")
                }
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
            StreamUtil.writeInt(stream, vertices.size)
            for (verticesElement in vertices) {
                verticesElement.writeTo(stream)
            }
            StreamUtil.writeInt(stream, primitiveType.tag)
        }
    }

    class PlacedText : DebugData {
        lateinit var vertex: model.ColoredVertex
        lateinit var text: String
        var alignment: Float = 0.0f
        var size: Float = 0.0f
        constructor() {}
        constructor(vertex: model.ColoredVertex, text: String, alignment: Float, size: Float) {
            this.vertex = vertex
            this.text = text
            this.alignment = alignment
            this.size = size
        }
        companion object {
            val TAG = 2
            @Throws(java.io.IOException::class)
            fun readFrom(stream: java.io.InputStream): PlacedText {
                val result = PlacedText()
                result.vertex = model.ColoredVertex.readFrom(stream)
                result.text = StreamUtil.readString(stream)
                result.alignment = StreamUtil.readFloat(stream)
                result.size = StreamUtil.readFloat(stream)
                return result
            }
        }
        @Throws(java.io.IOException::class)
        override fun writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, TAG)
            vertex.writeTo(stream)
            StreamUtil.writeString(stream, text)
            StreamUtil.writeFloat(stream, alignment)
            StreamUtil.writeFloat(stream, size)
        }
    }
}
