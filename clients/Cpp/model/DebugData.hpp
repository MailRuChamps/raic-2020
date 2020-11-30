#ifndef _MODEL_DEBUG_DATA_HPP_
#define _MODEL_DEBUG_DATA_HPP_

#include <memory>
#include "../Stream.hpp"
#include "Color.hpp"
#include "ColoredVertex.hpp"
#include "PrimitiveType.hpp"
#include "Vec2Float.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class DebugData {
public:
    class Log;
    class Primitives;
    class PlacedText;

    static std::shared_ptr<DebugData> readFrom(InputStream& stream);
    virtual void writeTo(OutputStream& stream) const = 0;
};

class DebugData::Log : public DebugData {
public:
    static const int TAG = 0;
public:
    std::string text;
    Log();
    Log(std::string text);
    static Log readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class DebugData::Primitives : public DebugData {
public:
    static const int TAG = 1;
public:
    std::vector<ColoredVertex> vertices;
    PrimitiveType primitiveType;
    Primitives();
    Primitives(std::vector<ColoredVertex> vertices, PrimitiveType primitiveType);
    static Primitives readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class DebugData::PlacedText : public DebugData {
public:
    static const int TAG = 2;
public:
    ColoredVertex vertex;
    std::string text;
    float alignment;
    float size;
    PlacedText();
    PlacedText(ColoredVertex vertex, std::string text, float alignment, float size);
    static PlacedText readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

#endif
