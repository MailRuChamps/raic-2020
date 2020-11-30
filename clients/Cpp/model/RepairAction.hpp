#ifndef _MODEL_REPAIR_ACTION_HPP_
#define _MODEL_REPAIR_ACTION_HPP_

#include "../Stream.hpp"
#include <string>

class RepairAction {
public:
    int target;
    RepairAction();
    RepairAction(int target);
    static RepairAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
    bool operator ==(const RepairAction& other) const;
};
namespace std {
    template<>
    struct hash<RepairAction> {
        size_t operator ()(const RepairAction& value) const;
    };
}

#endif
