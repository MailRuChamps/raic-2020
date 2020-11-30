package model

import util.StreamUtil

sealed abstract class EntityType (val tag: Int) {
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, tag)
    }
}

object EntityType {
    case object WALL extends EntityType(0)
    case object HOUSE extends EntityType(1)
    case object BUILDER_BASE extends EntityType(2)
    case object BUILDER_UNIT extends EntityType(3)
    case object MELEE_BASE extends EntityType(4)
    case object MELEE_UNIT extends EntityType(5)
    case object RANGED_BASE extends EntityType(6)
    case object RANGED_UNIT extends EntityType(7)
    case object RESOURCE extends EntityType(8)
    case object TURRET extends EntityType(9)
    def readFrom(stream: java.io.InputStream): EntityType = StreamUtil.readInt(stream) match {
        case 0 => WALL
        case 1 => HOUSE
        case 2 => BUILDER_BASE
        case 3 => BUILDER_UNIT
        case 4 => MELEE_BASE
        case 5 => MELEE_UNIT
        case 6 => RANGED_BASE
        case 7 => RANGED_UNIT
        case 8 => RESOURCE
        case 9 => TURRET
        case _ => throw new java.io.IOException("Unexpected tag value")
    }
}
