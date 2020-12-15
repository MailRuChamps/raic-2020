package model;

import util.StreamUtil;

public class ColoredVertex {
    private model.Vec2Float worldPos;
    public model.Vec2Float getWorldPos() { return worldPos; }
    public void setWorldPos(model.Vec2Float worldPos) { this.worldPos = worldPos; }
    private model.Vec2Float screenOffset;
    public model.Vec2Float getScreenOffset() { return screenOffset; }
    public void setScreenOffset(model.Vec2Float screenOffset) { this.screenOffset = screenOffset; }
    private model.Color color;
    public model.Color getColor() { return color; }
    public void setColor(model.Color color) { this.color = color; }
    public ColoredVertex() {}
    public ColoredVertex(model.Vec2Float worldPos, model.Vec2Float screenOffset, model.Color color) {
        this.worldPos = worldPos;
        this.screenOffset = screenOffset;
        this.color = color;
    }
    public static ColoredVertex readFrom(java.io.InputStream stream) throws java.io.IOException {
        ColoredVertex result = new ColoredVertex();
        if (StreamUtil.readBoolean(stream)) {
            result.worldPos = model.Vec2Float.readFrom(stream);
        } else {
            result.worldPos = null;
        }
        result.screenOffset = model.Vec2Float.readFrom(stream);
        result.color = model.Color.readFrom(stream);
        return result;
    }
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        if (worldPos == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            worldPos.writeTo(stream);
        }
        screenOffset.writeTo(stream);
        color.writeTo(stream);
    }
}
