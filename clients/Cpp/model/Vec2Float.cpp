#include "Vec2Float.hpp"

Vec2Float::Vec2Float() { }
Vec2Float::Vec2Float(float x, float y) : x(x), y(y) { }
Vec2Float Vec2Float::readFrom(InputStream& stream) {
    Vec2Float result;
    result.x = stream.readFloat();
    result.y = stream.readFloat();
    return result;
}
void Vec2Float::writeTo(OutputStream& stream) const {
    stream.write(x);
    stream.write(y);
}
