namespace Aicup2020.Model
{
    public struct RepairProperties
    {
        public Model.EntityType[] ValidTargets { get; set; }
        public int Power { get; set; }
        public RepairProperties(Model.EntityType[] validTargets, int power)
        {
            this.ValidTargets = validTargets;
            this.Power = power;
        }
        public static RepairProperties ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new RepairProperties();
            result.ValidTargets = new Model.EntityType[reader.ReadInt32()];
            for (int i = 0; i < result.ValidTargets.Length; i++)
            {
                switch (reader.ReadInt32())
                {
                case 0:
                    result.ValidTargets[i] = Model.EntityType.Wall;
                    break;
                case 1:
                    result.ValidTargets[i] = Model.EntityType.House;
                    break;
                case 2:
                    result.ValidTargets[i] = Model.EntityType.BuilderBase;
                    break;
                case 3:
                    result.ValidTargets[i] = Model.EntityType.BuilderUnit;
                    break;
                case 4:
                    result.ValidTargets[i] = Model.EntityType.MeleeBase;
                    break;
                case 5:
                    result.ValidTargets[i] = Model.EntityType.MeleeUnit;
                    break;
                case 6:
                    result.ValidTargets[i] = Model.EntityType.RangedBase;
                    break;
                case 7:
                    result.ValidTargets[i] = Model.EntityType.RangedUnit;
                    break;
                case 8:
                    result.ValidTargets[i] = Model.EntityType.Resource;
                    break;
                case 9:
                    result.ValidTargets[i] = Model.EntityType.Turret;
                    break;
                default:
                    throw new System.Exception("Unexpected tag value");
                }
            }
            result.Power = reader.ReadInt32();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(ValidTargets.Length);
            foreach (var ValidTargetsElement in ValidTargets)
            {
                writer.Write((int) (ValidTargetsElement));
            }
            writer.Write(Power);
        }
    }
}
