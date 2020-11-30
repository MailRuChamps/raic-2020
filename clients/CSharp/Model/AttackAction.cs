namespace Aicup2020.Model
{
    public struct AttackAction
    {
        public int? Target { get; set; }
        public Model.AutoAttack? AutoAttack { get; set; }
        public AttackAction(int? target, Model.AutoAttack? autoAttack)
        {
            this.Target = target;
            this.AutoAttack = autoAttack;
        }
        public static AttackAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new AttackAction();
            if (reader.ReadBoolean())
            {
                result.Target = reader.ReadInt32();
            } else
            {
                result.Target = null;
            }
            if (reader.ReadBoolean())
            {
                result.AutoAttack = Model.AutoAttack.ReadFrom(reader);
            } else
            {
                result.AutoAttack = null;
            }
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            if (!Target.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write(Target.Value);
            }
            if (!AutoAttack.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                AutoAttack.Value.WriteTo(writer);
            }
        }
    }
}
