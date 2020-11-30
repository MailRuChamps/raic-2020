package model

import "io"
import . "aicup2020/stream"

type Color struct {
    R float32
    G float32
    B float32
    A float32
}
func NewColor(r float32, g float32, b float32, a float32) Color {
    return Color {
        R: r,
        G: g,
        B: b,
        A: a,
    }
}
func ReadColor(reader io.Reader) Color {
    result := Color {}
    result.R = ReadFloat32(reader)
    result.G = ReadFloat32(reader)
    result.B = ReadFloat32(reader)
    result.A = ReadFloat32(reader)
    return result
}
func (value Color) Write(writer io.Writer) {
    WriteFloat32(writer, value.R)
    WriteFloat32(writer, value.G)
    WriteFloat32(writer, value.B)
    WriteFloat32(writer, value.A)
}
