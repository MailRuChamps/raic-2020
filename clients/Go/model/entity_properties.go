package model

import "io"
import . "aicup2020/stream"

type EntityProperties struct {
    Size int32
    BuildScore int32
    DestroyScore int32
    CanMove bool
    PopulationProvide int32
    PopulationUse int32
    MaxHealth int32
    InitialCost int32
    SightRange int32
    ResourcePerHealth int32
    Build *BuildProperties
    Attack *AttackProperties
    Repair *RepairProperties
}
func NewEntityProperties(size int32, buildScore int32, destroyScore int32, canMove bool, populationProvide int32, populationUse int32, maxHealth int32, initialCost int32, sightRange int32, resourcePerHealth int32, build *BuildProperties, attack *AttackProperties, repair *RepairProperties) EntityProperties {
    return EntityProperties {
        Size: size,
        BuildScore: buildScore,
        DestroyScore: destroyScore,
        CanMove: canMove,
        PopulationProvide: populationProvide,
        PopulationUse: populationUse,
        MaxHealth: maxHealth,
        InitialCost: initialCost,
        SightRange: sightRange,
        ResourcePerHealth: resourcePerHealth,
        Build: build,
        Attack: attack,
        Repair: repair,
    }
}
func ReadEntityProperties(reader io.Reader) EntityProperties {
    result := EntityProperties {}
    result.Size = ReadInt32(reader)
    result.BuildScore = ReadInt32(reader)
    result.DestroyScore = ReadInt32(reader)
    result.CanMove = ReadBool(reader)
    result.PopulationProvide = ReadInt32(reader)
    result.PopulationUse = ReadInt32(reader)
    result.MaxHealth = ReadInt32(reader)
    result.InitialCost = ReadInt32(reader)
    result.SightRange = ReadInt32(reader)
    result.ResourcePerHealth = ReadInt32(reader)
    if ReadBool(reader) {
        var BuildValue BuildProperties
        BuildValue = ReadBuildProperties(reader)
        result.Build = &BuildValue
    } else {
        result.Build = nil
    }
    if ReadBool(reader) {
        var AttackValue AttackProperties
        AttackValue = ReadAttackProperties(reader)
        result.Attack = &AttackValue
    } else {
        result.Attack = nil
    }
    if ReadBool(reader) {
        var RepairValue RepairProperties
        RepairValue = ReadRepairProperties(reader)
        result.Repair = &RepairValue
    } else {
        result.Repair = nil
    }
    return result
}
func (value EntityProperties) Write(writer io.Writer) {
    WriteInt32(writer, value.Size)
    WriteInt32(writer, value.BuildScore)
    WriteInt32(writer, value.DestroyScore)
    WriteBool(writer, value.CanMove)
    WriteInt32(writer, value.PopulationProvide)
    WriteInt32(writer, value.PopulationUse)
    WriteInt32(writer, value.MaxHealth)
    WriteInt32(writer, value.InitialCost)
    WriteInt32(writer, value.SightRange)
    WriteInt32(writer, value.ResourcePerHealth)
    if value.Build == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.Build).Write(writer)
    }
    if value.Attack == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.Attack).Write(writer)
    }
    if value.Repair == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        (*value.Repair).Write(writer)
    }
}
