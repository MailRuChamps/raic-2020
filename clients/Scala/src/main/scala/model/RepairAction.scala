package model

import util.StreamUtil

case class RepairAction(target: Int) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, target)
    }
}
object RepairAction {
    def readFrom(stream: java.io.InputStream): RepairAction = RepairAction(
        StreamUtil.readInt(stream)
        )
}
