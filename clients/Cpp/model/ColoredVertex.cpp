#include "ColoredVertex.hpp"

ColoredVertex::ColoredVertex() { }
ColoredVertex::ColoredVertex(std::shared_ptr<Vec2Float> worldPos, Vec2Float screenOffset, Color color) : worldPos(worldPos), screenOffset(screenOffset), color(color) { }
ColoredVertex ColoredVertex::readFrom(InputStream& stream) {
    ColoredVertex result;
    if (stream.readBool()) {
        result.worldPos = std::shared_ptr<Vec2Float>(new Vec2Float());
        *result.worldPos = Vec2Float::readFrom(stream);
    } else {
        result.worldPos = std::shared_ptr<Vec2Float>();
    }
    result.screenOffset = Vec2Float::readFrom(stream);
    result.color = Color::readFrom(stream);
    return result;
}
void ColoredVertex::writeTo(OutputStream& stream) const {
    if (worldPos) {
        stream.write(true);
        (*worldPos).writeTo(stream);
    } else {
        stream.write(false);
    }
    screenOffset.writeTo(stream);
    color.writeTo(stream);
}
