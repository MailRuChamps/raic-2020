package model

import "io"
import . "aicup2020/stream"

type Camera struct {
    Center Vec2Float32
    Rotation float32
    Attack float32
    Distance float32
    Perspective bool
}
func NewCamera(center Vec2Float32, rotation float32, attack float32, distance float32, perspective bool) Camera {
    return Camera {
        Center: center,
        Rotation: rotation,
        Attack: attack,
        Distance: distance,
        Perspective: perspective,
    }
}
func ReadCamera(reader io.Reader) Camera {
    result := Camera {}
    result.Center = ReadVec2Float32(reader)
    result.Rotation = ReadFloat32(reader)
    result.Attack = ReadFloat32(reader)
    result.Distance = ReadFloat32(reader)
    result.Perspective = ReadBool(reader)
    return result
}
func (value Camera) Write(writer io.Writer) {
    value.Center.Write(writer)
    WriteFloat32(writer, value.Rotation)
    WriteFloat32(writer, value.Attack)
    WriteFloat32(writer, value.Distance)
    WriteBool(writer, value.Perspective)
}
