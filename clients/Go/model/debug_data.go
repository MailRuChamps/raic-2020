package model

import "io"
import . "aicup2020/stream"

type DebugData interface {
    Write(writer io.Writer)
}
func ReadDebugData(reader io.Reader) DebugData {
    switch ReadInt32(reader) {
        case 0:
            return ReadDebugDataLog(reader)
        case 1:
            return ReadDebugDataPrimitives(reader)
        case 2:
            return ReadDebugDataPlacedText(reader)
    }
    panic("Unexpected tag value")
}

type DebugDataLog struct {
    Text string
}
func NewDebugDataLog(text string) DebugDataLog {
    return DebugDataLog {
        Text: text,
    }
}
func ReadDebugDataLog(reader io.Reader) DebugDataLog {
    result := DebugDataLog {}
    result.Text = ReadString(reader)
    return result
}
func (value DebugDataLog) Write(writer io.Writer) {
    WriteInt32(writer, 0)
    WriteString(writer, value.Text)
}

type DebugDataPrimitives struct {
    Vertices []ColoredVertex
    PrimitiveType PrimitiveType
}
func NewDebugDataPrimitives(vertices []ColoredVertex, primitiveType PrimitiveType) DebugDataPrimitives {
    return DebugDataPrimitives {
        Vertices: vertices,
        PrimitiveType: primitiveType,
    }
}
func ReadDebugDataPrimitives(reader io.Reader) DebugDataPrimitives {
    result := DebugDataPrimitives {}
    result.Vertices = make([]ColoredVertex, ReadInt32(reader))
    for i := range result.Vertices {
        result.Vertices[i] = ReadColoredVertex(reader)
    }
    result.PrimitiveType = ReadPrimitiveType(reader)
    return result
}
func (value DebugDataPrimitives) Write(writer io.Writer) {
    WriteInt32(writer, 1)
    WriteInt32(writer, int32(len(value.Vertices)))
    for _, VerticesElement := range value.Vertices {
        VerticesElement.Write(writer)
    }
    WriteInt32(writer, int32(value.PrimitiveType))
}

type DebugDataPlacedText struct {
    Vertex ColoredVertex
    Text string
    Alignment float32
    Size float32
}
func NewDebugDataPlacedText(vertex ColoredVertex, text string, alignment float32, size float32) DebugDataPlacedText {
    return DebugDataPlacedText {
        Vertex: vertex,
        Text: text,
        Alignment: alignment,
        Size: size,
    }
}
func ReadDebugDataPlacedText(reader io.Reader) DebugDataPlacedText {
    result := DebugDataPlacedText {}
    result.Vertex = ReadColoredVertex(reader)
    result.Text = ReadString(reader)
    result.Alignment = ReadFloat32(reader)
    result.Size = ReadFloat32(reader)
    return result
}
func (value DebugDataPlacedText) Write(writer io.Writer) {
    WriteInt32(writer, 2)
    value.Vertex.Write(writer)
    WriteString(writer, value.Text)
    WriteFloat32(writer, value.Alignment)
    WriteFloat32(writer, value.Size)
}
