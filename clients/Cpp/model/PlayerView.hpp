#ifndef _MODEL_PLAYER_VIEW_HPP_
#define _MODEL_PLAYER_VIEW_HPP_

#include "../Stream.hpp"
#include "AttackProperties.hpp"
#include "BuildProperties.hpp"
#include "Entity.hpp"
#include "EntityProperties.hpp"
#include "EntityType.hpp"
#include "Player.hpp"
#include "RepairProperties.hpp"
#include "Vec2Int.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

class PlayerView {
public:
    int myId;
    int mapSize;
    bool fogOfWar;
    std::unordered_map<EntityType, EntityProperties> entityProperties;
    int maxTickCount;
    int maxPathfindNodes;
    int currentTick;
    std::vector<Player> players;
    std::vector<Entity> entities;
    PlayerView();
    PlayerView(int myId, int mapSize, bool fogOfWar, std::unordered_map<EntityType, EntityProperties> entityProperties, int maxTickCount, int maxPathfindNodes, int currentTick, std::vector<Player> players, std::vector<Entity> entities);
    static PlayerView readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
