import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct AutoAttack {
    int pathfindRange;
    EntityType[] validTargets;
    this(int pathfindRange, EntityType[] validTargets) {
        this.pathfindRange = pathfindRange;
        this.validTargets = validTargets;
    }
    static AutoAttack readFrom(Stream reader) {
        auto result = AutoAttack();
        result.pathfindRange = reader.readInt();
        result.validTargets = new EntityType[reader.readInt()];
        for (int i = 0; i < result.validTargets.length; i++) {
            switch (reader.readInt()) {
            case 0:
                result.validTargets[i] = EntityType.Wall;
                break;
            case 1:
                result.validTargets[i] = EntityType.House;
                break;
            case 2:
                result.validTargets[i] = EntityType.BuilderBase;
                break;
            case 3:
                result.validTargets[i] = EntityType.BuilderUnit;
                break;
            case 4:
                result.validTargets[i] = EntityType.MeleeBase;
                break;
            case 5:
                result.validTargets[i] = EntityType.MeleeUnit;
                break;
            case 6:
                result.validTargets[i] = EntityType.RangedBase;
                break;
            case 7:
                result.validTargets[i] = EntityType.RangedUnit;
                break;
            case 8:
                result.validTargets[i] = EntityType.Resource;
                break;
            case 9:
                result.validTargets[i] = EntityType.Turret;
                break;
            default:
                throw new Exception("Unexpected tag value");
            }
        }
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(pathfindRange);
        writer.write(cast(int)(validTargets.length));
        foreach (validTargetsElement; validTargets) {
            writer.write(cast(int)(validTargetsElement));
        }
    }
    string toString() const {
        return "AutoAttack" ~ "(" ~
            to!string(pathfindRange) ~
            to!string(validTargets) ~
            ")";
    }
}
