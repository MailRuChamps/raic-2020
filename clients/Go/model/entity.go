package model

import "io"
import . "aicup2020/stream"

type Entity struct {
    Id int32
    PlayerId *int32
    EntityType EntityType
    Position Vec2Int32
    Health int32
    Active bool
}
func NewEntity(id int32, playerId *int32, entityType EntityType, position Vec2Int32, health int32, active bool) Entity {
    return Entity {
        Id: id,
        PlayerId: playerId,
        EntityType: entityType,
        Position: position,
        Health: health,
        Active: active,
    }
}
func ReadEntity(reader io.Reader) Entity {
    result := Entity {}
    result.Id = ReadInt32(reader)
    if ReadBool(reader) {
        var PlayerIdValue int32
        PlayerIdValue = ReadInt32(reader)
        result.PlayerId = &PlayerIdValue
    } else {
        result.PlayerId = nil
    }
    result.EntityType = ReadEntityType(reader)
    result.Position = ReadVec2Int32(reader)
    result.Health = ReadInt32(reader)
    result.Active = ReadBool(reader)
    return result
}
func (value Entity) Write(writer io.Writer) {
    WriteInt32(writer, value.Id)
    if value.PlayerId == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        WriteInt32(writer, (*value.PlayerId))
    }
    WriteInt32(writer, int32(value.EntityType))
    value.Position.Write(writer)
    WriteInt32(writer, value.Health)
    WriteBool(writer, value.Active)
}
