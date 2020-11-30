package model

import "io"
import . "aicup2020/stream"

type AttackProperties struct {
    AttackRange int32
    Damage int32
    CollectResource bool
}
func NewAttackProperties(attackRange int32, damage int32, collectResource bool) AttackProperties {
    return AttackProperties {
        AttackRange: attackRange,
        Damage: damage,
        CollectResource: collectResource,
    }
}
func ReadAttackProperties(reader io.Reader) AttackProperties {
    result := AttackProperties {}
    result.AttackRange = ReadInt32(reader)
    result.Damage = ReadInt32(reader)
    result.CollectResource = ReadBool(reader)
    return result
}
func (value AttackProperties) Write(writer io.Writer) {
    WriteInt32(writer, value.AttackRange)
    WriteInt32(writer, value.Damage)
    WriteBool(writer, value.CollectResource)
}
