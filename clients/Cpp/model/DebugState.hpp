#ifndef _MODEL_DEBUG_STATE_HPP_
#define _MODEL_DEBUG_STATE_HPP_

#include "../Stream.hpp"
#include "Camera.hpp"
#include "Vec2Float.hpp"
#include "Vec2Int.hpp"
#include <stdexcept>
#include <string>
#include <vector>

class DebugState {
public:
    Vec2Int windowSize;
    Vec2Float mousePosWindow;
    Vec2Float mousePosWorld;
    std::vector<std::string> pressedKeys;
    Camera camera;
    int playerIndex;
    DebugState();
    DebugState(Vec2Int windowSize, Vec2Float mousePosWindow, Vec2Float mousePosWorld, std::vector<std::string> pressedKeys, Camera camera, int playerIndex);
    static DebugState readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
