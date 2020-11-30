#ifndef _MODEL_COLORED_VERTEX_HPP_
#define _MODEL_COLORED_VERTEX_HPP_

#include "../Stream.hpp"
#include "Color.hpp"
#include "Vec2Float.hpp"
#include <memory>
#include <stdexcept>
#include <string>

class ColoredVertex {
public:
    std::shared_ptr<Vec2Float> worldPos;
    Vec2Float screenOffset;
    Color color;
    ColoredVertex();
    ColoredVertex(std::shared_ptr<Vec2Float> worldPos, Vec2Float screenOffset, Color color);
    static ColoredVertex readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
