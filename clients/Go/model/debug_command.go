package model

import "io"
import . "aicup2020/stream"

type DebugCommand interface {
    Write(writer io.Writer)
}
func ReadDebugCommand(reader io.Reader) DebugCommand {
    switch ReadInt32(reader) {
        case 0:
            return ReadDebugCommandAdd(reader)
        case 1:
            return ReadDebugCommandClear(reader)
    }
    panic("Unexpected tag value")
}

type DebugCommandAdd struct {
    Data DebugData
}
func NewDebugCommandAdd(data DebugData) DebugCommandAdd {
    return DebugCommandAdd {
        Data: data,
    }
}
func ReadDebugCommandAdd(reader io.Reader) DebugCommandAdd {
    result := DebugCommandAdd {}
    result.Data = ReadDebugData(reader)
    return result
}
func (value DebugCommandAdd) Write(writer io.Writer) {
    WriteInt32(writer, 0)
    value.Data.Write(writer)
}

type DebugCommandClear struct {
}
func NewDebugCommandClear() DebugCommandClear {
    return DebugCommandClear {
    }
}
func ReadDebugCommandClear(reader io.Reader) DebugCommandClear {
    result := DebugCommandClear {}
    return result
}
func (value DebugCommandClear) Write(writer io.Writer) {
    WriteInt32(writer, 1)
}
