package model

import "io"
import . "aicup2020/stream"

type EntityAction struct {
    MoveAction *MoveAction
    BuildAction *BuildAction
    AttackAction *AttackAction
    RepairAction *RepairAction
}
func NewEntityAction(moveAction *MoveAction, buildAction *BuildAction, attackAction *AttackAction, repairAction *RepairAction) EntityAction {
    return EntityAction {
        MoveAction: moveAction,
        BuildAction: buildAction,
        AttackAction: attackAction,
        RepairAction: repairAction,
    }
}
func ReadEntityAction(reader io.Reader) EntityAction {
    result := EntityAction {}
    if ReadBool(reader) {
        var MoveActionValue MoveAction
        MoveActionValue = ReadMoveAction(reader)
        result.MoveAction = &MoveActionValue
    } else {
        result.MoveAction = nil
    }
    if ReadBool(reader) {
        var BuildActionValue BuildAction
        BuildActionValue = ReadBuildAction(reader)
        result.BuildAction = &BuildActionValue
    } else {
        result.BuildAction = nil
    }
    if ReadBool(reader) {
        var AttackActionValue AttackAction
        AttackActionValue = ReadAttackAction(reader)
        result.AttackAction = &AttackActionValue
    } else {
        result.AttackAction = nil
    }
    if ReadBool(reader) {
        var RepairActionValue RepairAction
        RepairActionValue = ReadRepairAction(reader)
        result.RepairAction = &RepairActionValue
    } else {
        result.RepairAction = nil
    }
    return result
}
func (value EntityAction) Write(writer io.Writer) {
    if value.MoveAction == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.MoveAction).Write(writer)
    }
    if value.BuildAction == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.BuildAction).Write(writer)
    }
    if value.AttackAction == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.AttackAction).Write(writer)
    }
    if value.RepairAction == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.RepairAction).Write(writer)
    }
}
