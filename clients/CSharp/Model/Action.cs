namespace Aicup2020.Model
{
    public struct Action
    {
        public System.Collections.Generic.IDictionary<int, Model.EntityAction> EntityActions { get; set; }
        public Action(System.Collections.Generic.IDictionary<int, Model.EntityAction> entityActions)
        {
            this.EntityActions = entityActions;
        }
        public static Action ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Action();
            int EntityActionsSize = reader.ReadInt32();
            result.EntityActions = new System.Collections.Generic.Dictionary<int, Model.EntityAction>(EntityActionsSize);
            for (int i = 0; i < EntityActionsSize; i++)
            {
                int EntityActionsKey;
                EntityActionsKey = reader.ReadInt32();
                Model.EntityAction EntityActionsValue;
                EntityActionsValue = Model.EntityAction.ReadFrom(reader);
                result.EntityActions.Add(EntityActionsKey, EntityActionsValue);
            }
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(EntityActions.Count);
            foreach (var EntityActionsEntry in EntityActions)
            {
                var EntityActionsKey = EntityActionsEntry.Key;
                var EntityActionsValue = EntityActionsEntry.Value;
                writer.Write(EntityActionsKey);
                EntityActionsValue.WriteTo(writer);
            }
        }
    }
}
