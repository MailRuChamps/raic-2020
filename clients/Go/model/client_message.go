package model

import "io"
import . "aicup2020/stream"

type ClientMessage interface {
    Write(writer io.Writer)
}
func ReadClientMessage(reader io.Reader) ClientMessage {
    switch ReadInt32(reader) {
        case 0:
            return ReadClientMessageDebugMessage(reader)
        case 1:
            return ReadClientMessageActionMessage(reader)
        case 2:
            return ReadClientMessageDebugUpdateDone(reader)
        case 3:
            return ReadClientMessageRequestDebugState(reader)
    }
    panic("Unexpected tag value")
}

type ClientMessageDebugMessage struct {
    Command DebugCommand
}
func NewClientMessageDebugMessage(command DebugCommand) ClientMessageDebugMessage {
    return ClientMessageDebugMessage {
        Command: command,
    }
}
func ReadClientMessageDebugMessage(reader io.Reader) ClientMessageDebugMessage {
    result := ClientMessageDebugMessage {}
    result.Command = ReadDebugCommand(reader)
    return result
}
func (value ClientMessageDebugMessage) Write(writer io.Writer) {
    WriteInt32(writer, 0)
    value.Command.Write(writer)
}

type ClientMessageActionMessage struct {
    Action Action
}
func NewClientMessageActionMessage(action Action) ClientMessageActionMessage {
    return ClientMessageActionMessage {
        Action: action,
    }
}
func ReadClientMessageActionMessage(reader io.Reader) ClientMessageActionMessage {
    result := ClientMessageActionMessage {}
    result.Action = ReadAction(reader)
    return result
}
func (value ClientMessageActionMessage) Write(writer io.Writer) {
    WriteInt32(writer, 1)
    value.Action.Write(writer)
}

type ClientMessageDebugUpdateDone struct {
}
func NewClientMessageDebugUpdateDone() ClientMessageDebugUpdateDone {
    return ClientMessageDebugUpdateDone {
    }
}
func ReadClientMessageDebugUpdateDone(reader io.Reader) ClientMessageDebugUpdateDone {
    result := ClientMessageDebugUpdateDone {}
    return result
}
func (value ClientMessageDebugUpdateDone) Write(writer io.Writer) {
    WriteInt32(writer, 2)
}

type ClientMessageRequestDebugState struct {
}
func NewClientMessageRequestDebugState() ClientMessageRequestDebugState {
    return ClientMessageRequestDebugState {
    }
}
func ReadClientMessageRequestDebugState(reader io.Reader) ClientMessageRequestDebugState {
    result := ClientMessageRequestDebugState {}
    return result
}
func (value ClientMessageRequestDebugState) Write(writer io.Writer) {
    WriteInt32(writer, 3)
}
