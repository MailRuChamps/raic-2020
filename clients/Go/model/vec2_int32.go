package model

import "io"
import . "aicup2020/stream"

type Vec2Int32 struct {
    X int32
    Y int32
}
func NewVec2Int32(x int32, y int32) Vec2Int32 {
    return Vec2Int32 {
        X: x,
        Y: y,
    }
}
func ReadVec2Int32(reader io.Reader) Vec2Int32 {
    result := Vec2Int32 {}
    result.X = ReadInt32(reader)
    result.Y = ReadInt32(reader)
    return result
}
func (value Vec2Int32) Write(writer io.Writer) {
    WriteInt32(writer, value.X)
    WriteInt32(writer, value.Y)
}
