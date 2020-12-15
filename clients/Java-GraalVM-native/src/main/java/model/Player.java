package model;

import util.StreamUtil;

public class Player {
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    private int score;
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    private int resource;
    public int getResource() { return resource; }
    public void setResource(int resource) { this.resource = resource; }
    public Player() {}
    public Player(int id, int score, int resource) {
        this.id = id;
        this.score = score;
        this.resource = resource;
    }
    public static Player readFrom(java.io.InputStream stream) throws java.io.IOException {
        Player result = new Player();
        result.id = StreamUtil.readInt(stream);
        result.score = StreamUtil.readInt(stream);
        result.resource = StreamUtil.readInt(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, id);
        StreamUtil.writeInt(stream, score);
        StreamUtil.writeInt(stream, resource);
    }
}
