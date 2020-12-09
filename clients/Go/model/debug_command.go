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
        case 2:
            return ReadDebugCommandSetAutoFlush(reader)
        case 3:
            return ReadDebugCommandFlush(reader)
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

type DebugCommandSetAutoFlush struct {
    Enable bool
}
func NewDebugCommandSetAutoFlush(enable bool) DebugCommandSetAutoFlush {
    return DebugCommandSetAutoFlush {
        Enable: enable,
    }
}
func ReadDebugCommandSetAutoFlush(reader io.Reader) DebugCommandSetAutoFlush {
    result := DebugCommandSetAutoFlush {}
    result.Enable = ReadBool(reader)
    return result
}
func (value DebugCommandSetAutoFlush) Write(writer io.Writer) {
    WriteInt32(writer, 2)
    WriteBool(writer, value.Enable)
}

type DebugCommandFlush struct {
}
func NewDebugCommandFlush() DebugCommandFlush {
    return DebugCommandFlush {
    }
}
func ReadDebugCommandFlush(reader io.Reader) DebugCommandFlush {
    result := DebugCommandFlush {}
    return result
}
func (value DebugCommandFlush) Write(writer io.Writer) {
    WriteInt32(writer, 3)
}
