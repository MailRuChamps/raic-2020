package model

import "io"
import . "aicup2020/stream"

type PlayerView struct {
    MyId int32
    MapSize int32
    FogOfWar bool
    EntityProperties map[EntityType]EntityProperties
    MaxTickCount int32
    MaxPathfindNodes int32
    CurrentTick int32
    Players []Player
    Entities []Entity
}
func NewPlayerView(myId int32, mapSize int32, fogOfWar bool, entityProperties map[EntityType]EntityProperties, maxTickCount int32, maxPathfindNodes int32, currentTick int32, players []Player, entities []Entity) PlayerView {
    return PlayerView {
        MyId: myId,
        MapSize: mapSize,
        FogOfWar: fogOfWar,
        EntityProperties: entityProperties,
        MaxTickCount: maxTickCount,
        MaxPathfindNodes: maxPathfindNodes,
        CurrentTick: currentTick,
        Players: players,
        Entities: entities,
    }
}
func ReadPlayerView(reader io.Reader) PlayerView {
    result := PlayerView {}
    result.MyId = ReadInt32(reader)
    result.MapSize = ReadInt32(reader)
    result.FogOfWar = ReadBool(reader)
    EntityPropertiesSize := ReadInt32(reader)
    result.EntityProperties = make(map[EntityType]EntityProperties)
    for i := int32(0); i < EntityPropertiesSize; i++ {
        var EntityPropertiesKey EntityType
        EntityPropertiesKey = ReadEntityType(reader)
        var EntityPropertiesValue EntityProperties
        EntityPropertiesValue = ReadEntityProperties(reader)
        result.EntityProperties[EntityPropertiesKey] = EntityPropertiesValue
    }
    result.MaxTickCount = ReadInt32(reader)
    result.MaxPathfindNodes = ReadInt32(reader)
    result.CurrentTick = ReadInt32(reader)
    result.Players = make([]Player, ReadInt32(reader))
    for i := range result.Players {
        result.Players[i] = ReadPlayer(reader)
    }
    result.Entities = make([]Entity, ReadInt32(reader))
    for i := range result.Entities {
        result.Entities[i] = ReadEntity(reader)
    }
    return result
}
func (value PlayerView) Write(writer io.Writer) {
    WriteInt32(writer, value.MyId)
    WriteInt32(writer, value.MapSize)
    WriteBool(writer, value.FogOfWar)
    WriteInt32(writer, int32(len(value.EntityProperties)))
    for EntityPropertiesKey, EntityPropertiesValue := range value.EntityProperties {
        WriteInt32(writer, int32(EntityPropertiesKey))
        EntityPropertiesValue.Write(writer)
    }
    WriteInt32(writer, value.MaxTickCount)
    WriteInt32(writer, value.MaxPathfindNodes)
    WriteInt32(writer, value.CurrentTick)
    WriteInt32(writer, int32(len(value.Players)))
    for _, PlayersElement := range value.Players {
        PlayersElement.Write(writer)
    }
    WriteInt32(writer, int32(len(value.Entities)))
    for _, EntitiesElement := range value.Entities {
        EntitiesElement.Write(writer)
    }
}
