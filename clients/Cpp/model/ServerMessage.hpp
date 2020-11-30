#ifndef _MODEL_SERVER_MESSAGE_HPP_
#define _MODEL_SERVER_MESSAGE_HPP_

#include <memory>
#include "../Stream.hpp"
#include "AttackProperties.hpp"
#include "BuildProperties.hpp"
#include "Entity.hpp"
#include "EntityProperties.hpp"
#include "EntityType.hpp"
#include "Player.hpp"
#include "PlayerView.hpp"
#include "RepairProperties.hpp"
#include "Vec2Int.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

class ServerMessage {
public:
    class GetAction;
    class Finish;
    class DebugUpdate;

    static std::shared_ptr<ServerMessage> readFrom(InputStream& stream);
    virtual void writeTo(OutputStream& stream) const = 0;
};

class ServerMessage::GetAction : public ServerMessage {
public:
    static const int TAG = 0;
public:
    PlayerView playerView;
    bool debugAvailable;
    GetAction();
    GetAction(PlayerView playerView, bool debugAvailable);
    static GetAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class ServerMessage::Finish : public ServerMessage {
public:
    static const int TAG = 1;
public:
    Finish();
    static Finish readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

class ServerMessage::DebugUpdate : public ServerMessage {
public:
    static const int TAG = 2;
public:
    PlayerView playerView;
    DebugUpdate();
    DebugUpdate(PlayerView playerView);
    static DebugUpdate readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const override;
};

#endif
