#include "RepairAction.hpp"

RepairAction::RepairAction() { }
RepairAction::RepairAction(int target) : target(target) { }
RepairAction RepairAction::readFrom(InputStream& stream) {
    RepairAction result;
    result.target = stream.readInt();
    return result;
}
void RepairAction::writeTo(OutputStream& stream) const {
    stream.write(target);
}
bool RepairAction::operator ==(const RepairAction& other) const {
    return target == other.target;
}
size_t std::hash<RepairAction>::operator ()(const RepairAction& value) const {
    size_t result = 0;
    result ^= std::hash<int>{}(value.target) + 0x9e3779b9 + (result<<6) + (result>>2);
    return result;
}
