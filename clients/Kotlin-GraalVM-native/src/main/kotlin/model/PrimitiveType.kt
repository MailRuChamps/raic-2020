package model

import util.StreamUtil

enum class PrimitiveType private constructor(var tag: Int) {
    LINES(0),
    TRIANGLES(1)
}
