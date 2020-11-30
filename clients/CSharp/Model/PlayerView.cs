namespace Aicup2020.Model
{
    public struct PlayerView
    {
        public int MyId { get; set; }
        public int MapSize { get; set; }
        public bool FogOfWar { get; set; }
        public System.Collections.Generic.IDictionary<Model.EntityType, Model.EntityProperties> EntityProperties { get; set; }
        public int MaxTickCount { get; set; }
        public int MaxPathfindNodes { get; set; }
        public int CurrentTick { get; set; }
        public Model.Player[] Players { get; set; }
        public Model.Entity[] Entities { get; set; }
        public PlayerView(int myId, int mapSize, bool fogOfWar, System.Collections.Generic.IDictionary<Model.EntityType, Model.EntityProperties> entityProperties, int maxTickCount, int maxPathfindNodes, int currentTick, Model.Player[] players, Model.Entity[] entities)
        {
            this.MyId = myId;
            this.MapSize = mapSize;
            this.FogOfWar = fogOfWar;
            this.EntityProperties = entityProperties;
            this.MaxTickCount = maxTickCount;
            this.MaxPathfindNodes = maxPathfindNodes;
            this.CurrentTick = currentTick;
            this.Players = players;
            this.Entities = entities;
        }
        public static PlayerView ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new PlayerView();
            result.MyId = reader.ReadInt32();
            result.MapSize = reader.ReadInt32();
            result.FogOfWar = reader.ReadBoolean();
            int EntityPropertiesSize = reader.ReadInt32();
            result.EntityProperties = new System.Collections.Generic.Dictionary<Model.EntityType, Model.EntityProperties>(EntityPropertiesSize);
            for (int i = 0; i < EntityPropertiesSize; i++)
            {
                Model.EntityType EntityPropertiesKey;
                switch (reader.ReadInt32())
                {
                case 0:
                    EntityPropertiesKey = Model.EntityType.Wall;
                    break;
                case 1:
                    EntityPropertiesKey = Model.EntityType.House;
                    break;
                case 2:
                    EntityPropertiesKey = Model.EntityType.BuilderBase;
                    break;
                case 3:
                    EntityPropertiesKey = Model.EntityType.BuilderUnit;
                    break;
                case 4:
                    EntityPropertiesKey = Model.EntityType.MeleeBase;
                    break;
                case 5:
                    EntityPropertiesKey = Model.EntityType.MeleeUnit;
                    break;
                case 6:
                    EntityPropertiesKey = Model.EntityType.RangedBase;
                    break;
                case 7:
                    EntityPropertiesKey = Model.EntityType.RangedUnit;
                    break;
                case 8:
                    EntityPropertiesKey = Model.EntityType.Resource;
                    break;
                case 9:
                    EntityPropertiesKey = Model.EntityType.Turret;
                    break;
                default:
                    throw new System.Exception("Unexpected tag value");
                }
                Model.EntityProperties EntityPropertiesValue;
                EntityPropertiesValue = Model.EntityProperties.ReadFrom(reader);
                result.EntityProperties.Add(EntityPropertiesKey, EntityPropertiesValue);
            }
            result.MaxTickCount = reader.ReadInt32();
            result.MaxPathfindNodes = reader.ReadInt32();
            result.CurrentTick = reader.ReadInt32();
            result.Players = new Model.Player[reader.ReadInt32()];
            for (int i = 0; i < result.Players.Length; i++)
            {
                result.Players[i] = Model.Player.ReadFrom(reader);
            }
            result.Entities = new Model.Entity[reader.ReadInt32()];
            for (int i = 0; i < result.Entities.Length; i++)
            {
                result.Entities[i] = Model.Entity.ReadFrom(reader);
            }
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(MyId);
            writer.Write(MapSize);
            writer.Write(FogOfWar);
            writer.Write(EntityProperties.Count);
            foreach (var EntityPropertiesEntry in EntityProperties)
            {
                var EntityPropertiesKey = EntityPropertiesEntry.Key;
                var EntityPropertiesValue = EntityPropertiesEntry.Value;
                writer.Write((int) (EntityPropertiesKey));
                EntityPropertiesValue.WriteTo(writer);
            }
            writer.Write(MaxTickCount);
            writer.Write(MaxPathfindNodes);
            writer.Write(CurrentTick);
            writer.Write(Players.Length);
            foreach (var PlayersElement in Players)
            {
                PlayersElement.WriteTo(writer);
            }
            writer.Write(Entities.Length);
            foreach (var EntitiesElement in Entities)
            {
                EntitiesElement.WriteTo(writer);
            }
        }
    }
}
