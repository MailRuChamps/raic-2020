import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct RepairProperties {
    EntityType[] validTargets;
    int power;
    this(EntityType[] validTargets, int power) {
        this.validTargets = validTargets;
        this.power = power;
    }
    static RepairProperties readFrom(Stream reader) {
        auto result = RepairProperties();
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
        result.power = reader.readInt();
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(cast(int)(validTargets.length));
        foreach (validTargetsElement; validTargets) {
            writer.write(cast(int)(validTargetsElement));
        }
        writer.write(power);
    }
    string toString() const {
        return "RepairProperties" ~ "(" ~
            to!string(validTargets) ~
            to!string(power) ~
            ")";
    }
}
