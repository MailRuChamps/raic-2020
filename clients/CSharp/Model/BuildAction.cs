namespace Aicup2020.Model
{
    public struct BuildAction
    {
        public Model.EntityType EntityType { get; set; }
        public Model.Vec2Int Position { get; set; }
        public BuildAction(Model.EntityType entityType, Model.Vec2Int position)
        {
            this.EntityType = entityType;
            this.Position = position;
        }
        public static BuildAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new BuildAction();
            switch (reader.ReadInt32())
            {
            case 0:
                result.EntityType = Model.EntityType.Wall;
                break;
            case 1:
                result.EntityType = Model.EntityType.House;
                break;
            case 2:
                result.EntityType = Model.EntityType.BuilderBase;
                break;
            case 3:
                result.EntityType = Model.EntityType.BuilderUnit;
                break;
            case 4:
                result.EntityType = Model.EntityType.MeleeBase;
                break;
            case 5:
                result.EntityType = Model.EntityType.MeleeUnit;
                break;
            case 6:
                result.EntityType = Model.EntityType.RangedBase;
                break;
            case 7:
                result.EntityType = Model.EntityType.RangedUnit;
                break;
            case 8:
                result.EntityType = Model.EntityType.Resource;
                break;
            case 9:
                result.EntityType = Model.EntityType.Turret;
                break;
            default:
                throw new System.Exception("Unexpected tag value");
            }
            result.Position = Model.Vec2Int.ReadFrom(reader);
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write((int) (EntityType));
            Position.WriteTo(writer);
        }
    }
}
