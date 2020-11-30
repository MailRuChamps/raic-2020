package model

import "io"
import . "aicup2020/stream"

type ServerMessage interface {
    Write(writer io.Writer)
}
func ReadServerMessage(reader io.Reader) ServerMessage {
    switch ReadInt32(reader) {
        case 0:
            return ReadServerMessageGetAction(reader)
        case 1:
            return ReadServerMessageFinish(reader)
        case 2:
            return ReadServerMessageDebugUpdate(reader)
    }
    panic("Unexpected tag value")
}

type ServerMessageGetAction struct {
    PlayerView PlayerView
    DebugAvailable bool
}
func NewServerMessageGetAction(playerView PlayerView, debugAvailable bool) ServerMessageGetAction {
    return ServerMessageGetAction {
        PlayerView: playerView,
        DebugAvailable: debugAvailable,
    }
}
func ReadServerMessageGetAction(reader io.Reader) ServerMessageGetAction {
    result := ServerMessageGetAction {}
    result.PlayerView = ReadPlayerView(reader)
    result.DebugAvailable = ReadBool(reader)
    return result
}
func (value ServerMessageGetAction) Write(writer io.Writer) {
    WriteInt32(writer, 0)
    value.PlayerView.Write(writer)
    WriteBool(writer, value.DebugAvailable)
}

type ServerMessageFinish struct {
}
func NewServerMessageFinish() ServerMessageFinish {
    return ServerMessageFinish {
    }
}
func ReadServerMessageFinish(reader io.Reader) ServerMessageFinish {
    result := ServerMessageFinish {}
    return result
}
func (value ServerMessageFinish) Write(writer io.Writer) {
    WriteInt32(writer, 1)
}

type ServerMessageDebugUpdate struct {
    PlayerView PlayerView
}
func NewServerMessageDebugUpdate(playerView PlayerView) ServerMessageDebugUpdate {
    return ServerMessageDebugUpdate {
        PlayerView: playerView,
    }
}
func ReadServerMessageDebugUpdate(reader io.Reader) ServerMessageDebugUpdate {
    result := ServerMessageDebugUpdate {}
    result.PlayerView = ReadPlayerView(reader)
    return result
}
func (value ServerMessageDebugUpdate) Write(writer io.Writer) {
    WriteInt32(writer, 2)
    value.PlayerView.Write(writer)
}
