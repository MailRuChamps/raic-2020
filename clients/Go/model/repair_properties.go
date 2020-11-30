package model

import "io"
import . "aicup2020/stream"

type RepairProperties struct {
    ValidTargets []EntityType
    Power int32
}
func NewRepairProperties(validTargets []EntityType, power int32) RepairProperties {
    return RepairProperties {
        ValidTargets: validTargets,
        Power: power,
    }
}
func ReadRepairProperties(reader io.Reader) RepairProperties {
    result := RepairProperties {}
    result.ValidTargets = make([]EntityType, ReadInt32(reader))
    for i := range result.ValidTargets {
        result.ValidTargets[i] = ReadEntityType(reader)
    }
    result.Power = ReadInt32(reader)
    return result
}
func (value RepairProperties) Write(writer io.Writer) {
    WriteInt32(writer, int32(len(value.ValidTargets)))
    for _, ValidTargetsElement := range value.ValidTargets {
        WriteInt32(writer, int32(ValidTargetsElement))
    }
    WriteInt32(writer, value.Power)
}
