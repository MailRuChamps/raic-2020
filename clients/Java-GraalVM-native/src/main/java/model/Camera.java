package model;

import util.StreamUtil;

public class Camera {
    private model.Vec2Float center;
    public model.Vec2Float getCenter() { return center; }
    public void setCenter(model.Vec2Float center) { this.center = center; }
    private float rotation;
    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation; }
    private float attack;
    public float getAttack() { return attack; }
    public void setAttack(float attack) { this.attack = attack; }
    private float distance;
    public float getDistance() { return distance; }
    public void setDistance(float distance) { this.distance = distance; }
    private boolean perspective;
    public boolean isPerspective() { return perspective; }
    public void setPerspective(boolean perspective) { this.perspective = perspective; }
    public Camera() {}
    public Camera(model.Vec2Float center, float rotation, float attack, float distance, boolean perspective) {
        this.center = center;
        this.rotation = rotation;
        this.attack = attack;
        this.distance = distance;
        this.perspective = perspective;
    }
    public static Camera readFrom(java.io.InputStream stream) throws java.io.IOException {
        Camera result = new Camera();
        result.center = model.Vec2Float.readFrom(stream);
        result.rotation = StreamUtil.readFloat(stream);
        result.attack = StreamUtil.readFloat(stream);
        result.distance = StreamUtil.readFloat(stream);
        result.perspective = StreamUtil.readBoolean(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        center.writeTo(stream);
        StreamUtil.writeFloat(stream, rotation);
        StreamUtil.writeFloat(stream, attack);
        StreamUtil.writeFloat(stream, distance);
        StreamUtil.writeBoolean(stream, perspective);
    }
}
