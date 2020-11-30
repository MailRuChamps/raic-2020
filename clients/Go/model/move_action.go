package model

import "io"
import . "aicup2020/stream"

type MoveAction struct {
    Target Vec2Int32
    FindClosestPosition bool
    BreakThrough bool
}
func NewMoveAction(target Vec2Int32, findClosestPosition bool, breakThrough bool) MoveAction {
    return MoveAction {
        Target: target,
        FindClosestPosition: findClosestPosition,
        BreakThrough: breakThrough,
    }
}
func ReadMoveAction(reader io.Reader) MoveAction {
    result := MoveAction {}
    result.Target = ReadVec2Int32(reader)
    result.FindClosestPosition = ReadBool(reader)
    result.BreakThrough = ReadBool(reader)
    return result
}
func (value MoveAction) Write(writer io.Writer) {
    value.Target.Write(writer)
    WriteBool(writer, value.FindClosestPosition)
    WriteBool(writer, value.BreakThrough)
}
