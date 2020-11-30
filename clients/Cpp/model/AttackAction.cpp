#include "AttackAction.hpp"

AttackAction::AttackAction() { }
AttackAction::AttackAction(std::shared_ptr<int> target, std::shared_ptr<AutoAttack> autoAttack) : target(target), autoAttack(autoAttack) { }
AttackAction AttackAction::readFrom(InputStream& stream) {
    AttackAction result;
    if (stream.readBool()) {
        result.target = std::shared_ptr<int>(new int());
        *result.target = stream.readInt();
    } else {
        result.target = std::shared_ptr<int>();
    }
    if (stream.readBool()) {
        result.autoAttack = std::shared_ptr<AutoAttack>(new AutoAttack());
        *result.autoAttack = AutoAttack::readFrom(stream);
    } else {
        result.autoAttack = std::shared_ptr<AutoAttack>();
    }
    return result;
}
void AttackAction::writeTo(OutputStream& stream) const {
    if (target) {
        stream.write(true);
        stream.write((*target));
    } else {
        stream.write(false);
    }
    if (autoAttack) {
        stream.write(true);
        (*autoAttack).writeTo(stream);
    } else {
        stream.write(false);
    }
}
