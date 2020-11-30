package model

import util.StreamUtil

case class BuildAction(entityType: model.EntityType, position: model.Vec2Int) {
    def writeTo(stream: java.io.OutputStream) {
        entityType.writeTo(stream)
        position.writeTo(stream)
    }
}
object BuildAction {
    def readFrom(stream: java.io.InputStream): BuildAction = BuildAction(
        model.EntityType.readFrom(stream)
        ,
        model.Vec2Int.readFrom(stream)
        )
}
