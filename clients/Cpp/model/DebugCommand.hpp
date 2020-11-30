#ifndef _MODEL_DEBUG_COMMAND_HPP_
#define _MODEL_DEBUG_COMMAND_HPP_

#include <memory>
#include "../Stream.hpp"
#include "Color.hpp"
#include "ColoredVertex.hpp"
#include "DebugData.hpp"
#include "PrimitiveType.hpp"
#include "Vec2Float.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class DebugCommand {
public:
    class Add;
    class Clear;

    static std::shared_ptr<DebugCommand> readFrom(InputStream& stream);
    virtual void writeTo(OutputStream& stream) const = 0;
};

class DebugCommand::Add : public DebugCommand {
public:
    static const int TAG = 0;
public:
    std::shared_ptr<DebugData> data;
    Add();
    Add(std::shared_ptr<DebugData> data);
    static Add readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class DebugCommand::Clear : public DebugCommand {
public:
    static const int TAG = 1;
public:
    Clear();
    static Clear readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

#endif
