#ifndef _MODEL_VEC2_INT_HPP_
#define _MODEL_VEC2_INT_HPP_

#include "../Stream.hpp"
#include <string>

class Vec2Int {
public:
    int x;
    int y;
    Vec2Int();
    Vec2Int(int x, int y);
    static Vec2Int readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
    bool operator ==(const Vec2Int& other) const;
};
namespace std {
    template<>
    struct hash<Vec2Int> {
        size_t operator ()(const Vec2Int& value) const;
    };
}

#endif
