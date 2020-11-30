namespace Aicup2020.Model
{
    public struct Player
    {
        public int Id { get; set; }
        public int Score { get; set; }
        public int Resource { get; set; }
        public Player(int id, int score, int resource)
        {
            this.Id = id;
            this.Score = score;
            this.Resource = resource;
        }
        public static Player ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Player();
            result.Id = reader.ReadInt32();
            result.Score = reader.ReadInt32();
            result.Resource = reader.ReadInt32();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Id);
            writer.Write(Score);
            writer.Write(Resource);
        }
    }
}
