package model

import "io"
import . "aicup2020/stream"

type BuildAction struct {
    EntityType EntityType
    Position Vec2Int32
}
func NewBuildAction(entityType EntityType, position Vec2Int32) BuildAction {
    return BuildAction {
        EntityType: entityType,
        Position: position,
    }
}
func ReadBuildAction(reader io.Reader) BuildAction {
    result := BuildAction {}
    result.EntityType = ReadEntityType(reader)
    result.Position = ReadVec2Int32(reader)
    return result
}
func (value BuildAction) Write(writer io.Writer) {
    WriteInt32(writer, int32(value.EntityType))
    value.Position.Write(writer)
}
