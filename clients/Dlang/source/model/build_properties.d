import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct BuildProperties {
    EntityType[] options;
    Nullable!(int) initHealth;
    this(EntityType[] options, Nullable!(int) initHealth) {
        this.options = options;
        this.initHealth = initHealth;
    }
    static BuildProperties readFrom(Stream reader) {
        auto result = BuildProperties();
        result.options = new EntityType[reader.readInt()];
        for (int i = 0; i < result.options.length; i++) {
            switch (reader.readInt()) {
            case 0:
                result.options[i] = EntityType.Wall;
                break;
            case 1:
                result.options[i] = EntityType.House;
                break;
            case 2:
                result.options[i] = EntityType.BuilderBase;
                break;
            case 3:
                result.options[i] = EntityType.BuilderUnit;
                break;
            case 4:
                result.options[i] = EntityType.MeleeBase;
                break;
            case 5:
                result.options[i] = EntityType.MeleeUnit;
                break;
            case 6:
                result.options[i] = EntityType.RangedBase;
                break;
            case 7:
                result.options[i] = EntityType.RangedUnit;
                break;
            case 8:
                result.options[i] = EntityType.Resource;
                break;
            case 9:
                result.options[i] = EntityType.Turret;
                break;
            default:
                throw new Exception("Unexpected tag value");
            }
        }
        if (reader.readBool()) {
            result.initHealth = reader.readInt();
        } else {
            result.initHealth.nullify();
        }
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(cast(int)(options.length));
        foreach (optionsElement; options) {
            writer.write(cast(int)(optionsElement));
        }
        if (initHealth.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            writer.write(initHealth.get);
        }
    }
    string toString() const {
        return "BuildProperties" ~ "(" ~
            to!string(options) ~
            to!string(initHealth) ~
            ")";
    }
}
