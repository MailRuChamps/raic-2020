package model

import "io"
import . "aicup2020/stream"

type DebugState struct {
    WindowSize Vec2Int32
    MousePosWindow Vec2Float32
    MousePosWorld Vec2Float32
    PressedKeys []string
    Camera Camera
    PlayerIndex int32
}
func NewDebugState(windowSize Vec2Int32, mousePosWindow Vec2Float32, mousePosWorld Vec2Float32, pressedKeys []string, camera Camera, playerIndex int32) DebugState {
    return DebugState {
        WindowSize: windowSize,
        MousePosWindow: mousePosWindow,
        MousePosWorld: mousePosWorld,
        PressedKeys: pressedKeys,
        Camera: camera,
        PlayerIndex: playerIndex,
    }
}
func ReadDebugState(reader io.Reader) DebugState {
    result := DebugState {}
    result.WindowSize = ReadVec2Int32(reader)
    result.MousePosWindow = ReadVec2Float32(reader)
    result.MousePosWorld = ReadVec2Float32(reader)
    result.PressedKeys = make([]string, ReadInt32(reader))
    for i := range result.PressedKeys {
        result.PressedKeys[i] = ReadString(reader)
    }
    result.Camera = ReadCamera(reader)
    result.PlayerIndex = ReadInt32(reader)
    return result
}
func (value DebugState) Write(writer io.Writer) {
    value.WindowSize.Write(writer)
    value.MousePosWindow.Write(writer)
    value.MousePosWorld.Write(writer)
    WriteInt32(writer, int32(len(value.PressedKeys)))
    for _, PressedKeysElement := range value.PressedKeys {
        WriteString(writer, PressedKeysElement)
    }
    value.Camera.Write(writer)
    WriteInt32(writer, value.PlayerIndex)
}
