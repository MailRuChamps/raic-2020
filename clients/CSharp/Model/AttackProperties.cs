namespace Aicup2020.Model
{
    public struct AttackProperties
    {
        public int AttackRange { get; set; }
        public int Damage { get; set; }
        public bool CollectResource { get; set; }
        public AttackProperties(int attackRange, int damage, bool collectResource)
        {
            this.AttackRange = attackRange;
            this.Damage = damage;
            this.CollectResource = collectResource;
        }
        public static AttackProperties ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new AttackProperties();
            result.AttackRange = reader.ReadInt32();
            result.Damage = reader.ReadInt32();
            result.CollectResource = reader.ReadBoolean();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(AttackRange);
            writer.Write(Damage);
            writer.Write(CollectResource);
        }
    }
}
