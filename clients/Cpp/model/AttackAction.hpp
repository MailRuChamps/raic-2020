#ifndef _MODEL_ATTACK_ACTION_HPP_
#define _MODEL_ATTACK_ACTION_HPP_

#include "../Stream.hpp"
#include "AutoAttack.hpp"
#include "EntityType.hpp"
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>

class AttackAction {
public:
    std::shared_ptr<int> target;
    std::shared_ptr<AutoAttack> autoAttack;
    AttackAction();
    AttackAction(std::shared_ptr<int> target, std::shared_ptr<AutoAttack> autoAttack);
    static AttackAction readFrom(InputStream& stream);
    void writeTo(OutputStream& stream) const;
};

#endif
