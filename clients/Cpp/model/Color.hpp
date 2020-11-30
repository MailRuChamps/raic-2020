#ifndef _MODEL_COLOR_HPP_
#define _MODEL_COLOR_HPP_

#include "../Stream.hpp"
#include <string>

class Color {
public:
    float r;
    float g;
    float b;
    float a;
    Color();
    Color(float r, float g, float b, float a);
    static Color readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
