#include "Color.hpp"

Color::Color() { }
Color::Color(float r, float g, float b, float a) : r(r), g(g), b(b), a(a) { }
Color Color::readFrom(InputStream& stream) {
    Color result;
    result.r = stream.readFloat();
    result.g = stream.readFloat();
    result.b = stream.readFloat();
    result.a = stream.readFloat();
    return result;
}
void Color::writeTo(OutputStream& stream) const {
    stream.write(r);
    stream.write(g);
    stream.write(b);
    stream.write(a);
}
