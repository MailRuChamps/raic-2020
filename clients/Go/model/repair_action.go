package model

import "io"
import . "aicup2020/stream"

type RepairAction struct {
    Target int32
}
func NewRepairAction(target int32) RepairAction {
    return RepairAction {
        Target: target,
    }
}
func ReadRepairAction(reader io.Reader) RepairAction {
    result := RepairAction {}
    result.Target = ReadInt32(reader)
    return result
}
func (value RepairAction) Write(writer io.Writer) {
    WriteInt32(writer, value.Target)
}
