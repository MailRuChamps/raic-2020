#include "MoveAction.hpp"

MoveAction::MoveAction() { }
MoveAction::MoveAction(Vec2Int target, bool findClosestPosition, bool breakThrough) : target(target), findClosestPosition(findClosestPosition), breakThrough(breakThrough) { }
MoveAction MoveAction::readFrom(InputStream& stream) {
    MoveAction result;
    result.target = Vec2Int::readFrom(stream);
    result.findClosestPosition = stream.readBool();
    result.breakThrough = stream.readBool();
    return result;
}
void MoveAction::writeTo(OutputStream& stream) const {
    target.writeTo(stream);
    stream.write(findClosestPosition);
    stream.write(breakThrough);
}
bool MoveAction::operator ==(const MoveAction& other) const {
    return target == other.target && findClosestPosition == other.findClosestPosition && breakThrough == other.breakThrough;
}
size_t std::hash<MoveAction>::operator ()(const MoveAction& value) const {
    size_t result = 0;
    result ^= std::hash<Vec2Int>{}(value.target) + 0x9e3779b9 + (result<<6) + (result>>2);
    result ^= std::hash<bool>{}(value.findClosestPosition) + 0x9e3779b9 + (result<<6) + (result>>2);
    result ^= std::hash<bool>{}(value.breakThrough) + 0x9e3779b9 + (result<<6) + (result>>2);
    return result;
}
