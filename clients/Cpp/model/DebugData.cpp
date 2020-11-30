#include "DebugData.hpp"
#include <stdexcept>

DebugData::Log::Log() { }
DebugData::Log::Log(std::string text) : text(text) { }
DebugData::Log DebugData::Log::readFrom(InputStream& stream) {
    DebugData::Log result;
    result.text = stream.readString();
    return result;
}
void DebugData::Log::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    stream.write(text);
}

DebugData::Primitives::Primitives() { }
DebugData::Primitives::Primitives(std::vector<ColoredVertex> vertices, PrimitiveType primitiveType) : vertices(vertices), primitiveType(primitiveType) { }
DebugData::Primitives DebugData::Primitives::readFrom(InputStream& stream) {
    DebugData::Primitives result;
    result.vertices = std::vector<ColoredVertex>(stream.readInt());
    for (size_t i = 0; i < result.vertices.size(); i++) {
        result.vertices[i] = ColoredVertex::readFrom(stream);
    }
    switch (stream.readInt()) {
    case 0:
        result.primitiveType = PrimitiveType::LINES;
        break;
    case 1:
        result.primitiveType = PrimitiveType::TRIANGLES;
        break;
    default:
        throw std::runtime_error("Unexpected tag value");
    }
    return result;
}
void DebugData::Primitives::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    stream.write((int)(vertices.size()));
    for (const ColoredVertex& verticesElement : vertices) {
        verticesElement.writeTo(stream);
    }
    stream.write((int)(primitiveType));
}

DebugData::PlacedText::PlacedText() { }
DebugData::PlacedText::PlacedText(ColoredVertex vertex, std::string text, float alignment, float size) : vertex(vertex), text(text), alignment(alignment), size(size) { }
DebugData::PlacedText DebugData::PlacedText::readFrom(InputStream& stream) {
    DebugData::PlacedText result;
    result.vertex = ColoredVertex::readFrom(stream);
    result.text = stream.readString();
    result.alignment = stream.readFloat();
    result.size = stream.readFloat();
    return result;
}
void DebugData::PlacedText::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    vertex.writeTo(stream);
    stream.write(text);
    stream.write(alignment);
    stream.write(size);
}
std::shared_ptr<DebugData> DebugData::readFrom(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return std::shared_ptr<DebugData::Log>(new DebugData::Log(DebugData::Log::readFrom(stream)));
    case 1:
        return std::shared_ptr<DebugData::Primitives>(new DebugData::Primitives(DebugData::Primitives::readFrom(stream)));
    case 2:
        return std::shared_ptr<DebugData::PlacedText>(new DebugData::PlacedText(DebugData::PlacedText::readFrom(stream)));
    default:
        throw std::runtime_error("Unexpected tag value");
    }
};
