#ifndef _MODEL_CAMERA_HPP_
#define _MODEL_CAMERA_HPP_

#include "../Stream.hpp"
#include "Vec2Float.hpp"
#include <stdexcept>
#include <string>

class Camera {
public:
    Vec2Float center;
    float rotation;
    float attack;
    float distance;
    bool perspective;
    Camera();
    Camera(Vec2Float center, float rotation, float attack, float distance, bool perspective);
    static Camera readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
