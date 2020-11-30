#include "Camera.hpp"

Camera::Camera() { }
Camera::Camera(Vec2Float center, float rotation, float attack, float distance, bool perspective) : center(center), rotation(rotation), attack(attack), distance(distance), perspective(perspective) { }
Camera Camera::readFrom(InputStream& stream) {
    Camera result;
    result.center = Vec2Float::readFrom(stream);
    result.rotation = stream.readFloat();
    result.attack = stream.readFloat();
    result.distance = stream.readFloat();
    result.perspective = stream.readBool();
    return result;
}
void Camera::writeTo(OutputStream& stream) const {
    center.writeTo(stream);
    stream.write(rotation);
    stream.write(attack);
    stream.write(distance);
    stream.write(perspective);
}
