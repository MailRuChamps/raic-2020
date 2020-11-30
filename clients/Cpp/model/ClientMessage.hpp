#ifndef _MODEL_CLIENT_MESSAGE_HPP_
#define _MODEL_CLIENT_MESSAGE_HPP_

#include <memory>
#include "../Stream.hpp"
#include "Action.hpp"
#include "AttackAction.hpp"
#include "AutoAttack.hpp"
#include "BuildAction.hpp"
#include "Color.hpp"
#include "ColoredVertex.hpp"
#include "DebugCommand.hpp"
#include "DebugData.hpp"
#include "EntityAction.hpp"
#include "EntityType.hpp"
#include "MoveAction.hpp"
#include "PrimitiveType.hpp"
#include "RepairAction.hpp"
#include "Vec2Float.hpp"
#include "Vec2Int.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

class ClientMessage {
public:
    class DebugMessage;
    class ActionMessage;
    class DebugUpdateDone;
    class RequestDebugState;

    static std::shared_ptr<ClientMessage> readFrom(InputStream& stream);
    virtual void writeTo(OutputStream& stream) const = 0;
};

class ClientMessage::DebugMessage : public ClientMessage {
public:
    static const int TAG = 0;
public:
    std::shared_ptr<DebugCommand> command;
    DebugMessage();
    DebugMessage(std::shared_ptr<DebugCommand> command);
    static DebugMessage readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class ClientMessage::ActionMessage : public ClientMessage {
public:
    static const int TAG = 1;
public:
    Action action;
    ActionMessage();
    ActionMessage(Action action);
    static ActionMessage readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class ClientMessage::DebugUpdateDone : public ClientMessage {
public:
    static const int TAG = 2;
public:
    DebugUpdateDone();
    static DebugUpdateDone readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class ClientMessage::RequestDebugState : public ClientMessage {
public:
    static const int TAG = 3;
public:
    RequestDebugState();
    static RequestDebugState readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

#endif
