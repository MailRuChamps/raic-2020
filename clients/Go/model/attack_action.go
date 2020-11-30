package model

import "io"
import . "aicup2020/stream"

type AttackAction struct {
    Target *int32
    AutoAttack *AutoAttack
}
func NewAttackAction(target *int32, autoAttack *AutoAttack) AttackAction {
    return AttackAction {
        Target: target,
        AutoAttack: autoAttack,
    }
}
func ReadAttackAction(reader io.Reader) AttackAction {
    result := AttackAction {}
    if ReadBool(reader) {
        var TargetValue int32
        TargetValue = ReadInt32(reader)
        result.Target = &TargetValue
    } else {
        result.Target = nil
    }
    if ReadBool(reader) {
        var AutoAttackValue AutoAttack
        AutoAttackValue = ReadAutoAttack(reader)
        result.AutoAttack = &AutoAttackValue
    } else {
        result.AutoAttack = nil
    }
    return result
}
func (value AttackAction) Write(writer io.Writer) {
    if value.Target == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        WriteInt32(writer, (*value.Target))
    }
    if value.AutoAttack == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.AutoAttack).Write(writer)
    }
}
