package model

import "io"
import . "aicup2020/stream"

type EntityType int32
const (
    EntityTypeWall EntityType = 0
    EntityTypeHouse EntityType = 1
    EntityTypeBuilderBase EntityType = 2
    EntityTypeBuilderUnit EntityType = 3
    EntityTypeMeleeBase EntityType = 4
    EntityTypeMeleeUnit EntityType = 5
    EntityTypeRangedBase EntityType = 6
    EntityTypeRangedUnit EntityType = 7
    EntityTypeResource EntityType = 8
    EntityTypeTurret EntityType = 9
)
func ReadEntityType(reader io.Reader) EntityType {
    switch ReadInt32(reader) {
    case 0:
        return EntityTypeWall
    case 1:
        return EntityTypeHouse
    case 2:
        return EntityTypeBuilderBase
    case 3:
        return EntityTypeBuilderUnit
    case 4:
        return EntityTypeMeleeBase
    case 5:
        return EntityTypeMeleeUnit
    case 6:
        return EntityTypeRangedBase
    case 7:
        return EntityTypeRangedUnit
    case 8:
        return EntityTypeResource
    case 9:
        return EntityTypeTurret
    }
    panic("Unexpected tag value")
}
