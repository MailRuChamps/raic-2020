package model

import "io"
import . "aicup2020/stream"

type Action struct {
    EntityActions map[int32]EntityAction
}
func NewAction(entityActions map[int32]EntityAction) Action {
    return Action {
        EntityActions: entityActions,
    }
}
func ReadAction(reader io.Reader) Action {
    result := Action {}
    EntityActionsSize := ReadInt32(reader)
    result.EntityActions = make(map[int32]EntityAction)
    for i := int32(0); i < EntityActionsSize; i++ {
        var EntityActionsKey int32
        EntityActionsKey = ReadInt32(reader)
        var EntityActionsValue EntityAction
        EntityActionsValue = ReadEntityAction(reader)
        result.EntityActions[EntityActionsKey] = EntityActionsValue
    }
    return result
}
func (value Action) Write(writer io.Writer) {
    WriteInt32(writer, int32(len(value.EntityActions)))
    for EntityActionsKey, EntityActionsValue := range value.EntityActions {
        WriteInt32(writer, EntityActionsKey)
        EntityActionsValue.Write(writer)
    }
}
