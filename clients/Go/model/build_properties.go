package model

import "io"
import . "aicup2020/stream"

type BuildProperties struct {
    Options []EntityType
    InitHealth *int32
}
func NewBuildProperties(options []EntityType, initHealth *int32) BuildProperties {
    return BuildProperties {
        Options: options,
        InitHealth: initHealth,
    }
}
func ReadBuildProperties(reader io.Reader) BuildProperties {
    result := BuildProperties {}
    result.Options = make([]EntityType, ReadInt32(reader))
    for i := range result.Options {
        result.Options[i] = ReadEntityType(reader)
    }
    if ReadBool(reader) {
        var InitHealthValue int32
        InitHealthValue = ReadInt32(reader)
        result.InitHealth = &InitHealthValue
    } else {
        result.InitHealth = nil
    }
    return result
}
func (value BuildProperties) Write(writer io.Writer) {
    WriteInt32(writer, int32(len(value.Options)))
    for _, OptionsElement := range value.Options {
        WriteInt32(writer, int32(OptionsElement))
    }
    if value.InitHealth == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        WriteInt32(writer, (*value.InitHealth))
    }
}
