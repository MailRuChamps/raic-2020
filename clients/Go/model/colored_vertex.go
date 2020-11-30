package model

import "io"
import . "aicup2020/stream"

type ColoredVertex struct {
    WorldPos *Vec2Float32
    ScreenOffset Vec2Float32
    Color Color
}
func NewColoredVertex(worldPos *Vec2Float32, screenOffset Vec2Float32, color Color) ColoredVertex {
    return ColoredVertex {
        WorldPos: worldPos,
        ScreenOffset: screenOffset,
        Color: color,
    }
}
func ReadColoredVertex(reader io.Reader) ColoredVertex {
    result := ColoredVertex {}
    if ReadBool(reader) {
        var WorldPosValue Vec2Float32
        WorldPosValue = ReadVec2Float32(reader)
        result.WorldPos = &WorldPosValue
    } else {
        result.WorldPos = nil
    }
    result.ScreenOffset = ReadVec2Float32(reader)
    result.Color = ReadColor(reader)
    return result
}
func (value ColoredVertex) Write(writer io.Writer) {
    if value.WorldPos == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.WorldPos).Write(writer)
    }
    value.ScreenOffset.Write(writer)
    value.Color.Write(writer)
}
