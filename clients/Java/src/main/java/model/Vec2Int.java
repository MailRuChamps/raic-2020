package model;

import util.StreamUtil;

public class Vec2Int {
    private int x;
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    private int y;
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Vec2Int() {}
    public Vec2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Vec2Int readFrom(java.io.InputStream stream) throws java.io.IOException {
        Vec2Int result = new Vec2Int();
        result.x = StreamUtil.readInt(stream);
        result.y = StreamUtil.readInt(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, x);
        StreamUtil.writeInt(stream, y);
    }
}
