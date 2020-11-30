package model

import util.StreamUtil

case class BuildProperties(options: Seq[model.EntityType], initHealth: Option[Int]) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, options.length)
        options.foreach { value =>
            value.writeTo(stream)
        }
        initHealth match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                StreamUtil.writeInt(stream, value)
            }
        }
    }
}
object BuildProperties {
    def readFrom(stream: java.io.InputStream): BuildProperties = BuildProperties(
        (0 until StreamUtil.readInt(stream)).map { _ =>
            model.EntityType.readFrom(stream)
        }
        ,
        if (StreamUtil.readBoolean(stream)) Some(
            StreamUtil.readInt(stream)
        ) else None
        )
}
