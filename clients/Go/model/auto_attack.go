package model

import "io"
import . "aicup2020/stream"

type AutoAttack struct {
    PathfindRange int32
    ValidTargets []EntityType
}
func NewAutoAttack(pathfindRange int32, validTargets []EntityType) AutoAttack {
    return AutoAttack {
        PathfindRange: pathfindRange,
        ValidTargets: validTargets,
    }
}
func ReadAutoAttack(reader io.Reader) AutoAttack {
    result := AutoAttack {}
    result.PathfindRange = ReadInt32(reader)
    result.ValidTargets = make([]EntityType, ReadInt32(reader))
    for i := range result.ValidTargets {
        result.ValidTargets[i] = ReadEntityType(reader)
    }
    return result
}
func (value AutoAttack) Write(writer io.Writer) {
    WriteInt32(writer, value.PathfindRange)
    WriteInt32(writer, int32(len(value.ValidTargets)))
    for _, ValidTargetsElement := range value.ValidTargets {
        WriteInt32(writer, int32(ValidTargetsElement))
    }
}
