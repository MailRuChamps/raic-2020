namespace Aicup2020.Model
{
    public struct EntityProperties
    {
        public int Size { get; set; }
        public int BuildScore { get; set; }
        public int DestroyScore { get; set; }
        public bool CanMove { get; set; }
        public int PopulationProvide { get; set; }
        public int PopulationUse { get; set; }
        public int MaxHealth { get; set; }
        public int Cost { get; set; }
        public int SightRange { get; set; }
        public int ResourcePerHealth { get; set; }
        public Model.BuildProperties? Build { get; set; }
        public Model.AttackProperties? Attack { get; set; }
        public Model.RepairProperties? Repair { get; set; }
        public EntityProperties(int size, int buildScore, int destroyScore, bool canMove, int populationProvide, int populationUse, int maxHealth, int cost, int sightRange, int resourcePerHealth, Model.BuildProperties? build, Model.AttackProperties? attack, Model.RepairProperties? repair)
        {
            this.Size = size;
            this.BuildScore = buildScore;
            this.DestroyScore = destroyScore;
            this.CanMove = canMove;
            this.PopulationProvide = populationProvide;
            this.PopulationUse = populationUse;
            this.MaxHealth = maxHealth;
            this.Cost = cost;
            this.SightRange = sightRange;
            this.ResourcePerHealth = resourcePerHealth;
            this.Build = build;
            this.Attack = attack;
            this.Repair = repair;
        }
        public static EntityProperties ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new EntityProperties();
            result.Size = reader.ReadInt32();
            result.BuildScore = reader.ReadInt32();
            result.DestroyScore = reader.ReadInt32();
            result.CanMove = reader.ReadBoolean();
            result.PopulationProvide = reader.ReadInt32();
            result.PopulationUse = reader.ReadInt32();
            result.MaxHealth = reader.ReadInt32();
            result.Cost = reader.ReadInt32();
            result.SightRange = reader.ReadInt32();
            result.ResourcePerHealth = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.Build = Model.BuildProperties.ReadFrom(reader);
            } else
            {
                result.Build = null;
            }
            if (reader.ReadBoolean())
            {
                result.Attack = Model.AttackProperties.ReadFrom(reader);
            } else
            {
                result.Attack = null;
            }
            if (reader.ReadBoolean())
            {
                result.Repair = Model.RepairProperties.ReadFrom(reader);
            } else
            {
                result.Repair = null;
            }
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Size);
            writer.Write(BuildScore);
            writer.Write(DestroyScore);
            writer.Write(CanMove);
            writer.Write(PopulationProvide);
            writer.Write(PopulationUse);
            writer.Write(MaxHealth);
            writer.Write(Cost);
            writer.Write(SightRange);
            writer.Write(ResourcePerHealth);
            if (!Build.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                Build.Value.WriteTo(writer);
            }
            if (!Attack.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                Attack.Value.WriteTo(writer);
            }
            if (!Repair.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                Repair.Value.WriteTo(writer);
            }
        }
    }
}
