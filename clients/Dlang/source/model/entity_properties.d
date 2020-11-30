import model;
import stream;
import std.conv;
import std.typecons : Nullable;

struct EntityProperties {
    int size;
    int buildScore;
    int destroyScore;
    bool canMove;
    int populationProvide;
    int populationUse;
    int maxHealth;
    int cost;
    int sightRange;
    int resourcePerHealth;
    Nullable!(BuildProperties) build;
    Nullable!(AttackProperties) attack;
    Nullable!(RepairProperties) repair;
    this(int size, int buildScore, int destroyScore, bool canMove, int populationProvide, int populationUse, int maxHealth, int cost, int sightRange, int resourcePerHealth, Nullable!(BuildProperties) build, Nullable!(AttackProperties) attack, Nullable!(RepairProperties) repair) {
        this.size = size;
        this.buildScore = buildScore;
        this.destroyScore = destroyScore;
        this.canMove = canMove;
        this.populationProvide = populationProvide;
        this.populationUse = populationUse;
        this.maxHealth = maxHealth;
        this.cost = cost;
        this.sightRange = sightRange;
        this.resourcePerHealth = resourcePerHealth;
        this.build = build;
        this.attack = attack;
        this.repair = repair;
    }
    static EntityProperties readFrom(Stream reader) {
        auto result = EntityProperties();
        result.size = reader.readInt();
        result.buildScore = reader.readInt();
        result.destroyScore = reader.readInt();
        result.canMove = reader.readBool();
        result.populationProvide = reader.readInt();
        result.populationUse = reader.readInt();
        result.maxHealth = reader.readInt();
        result.cost = reader.readInt();
        result.sightRange = reader.readInt();
        result.resourcePerHealth = reader.readInt();
        if (reader.readBool()) {
            result.build = BuildProperties.readFrom(reader);
        } else {
            result.build.nullify();
        }
        if (reader.readBool()) {
            result.attack = AttackProperties.readFrom(reader);
        } else {
            result.attack.nullify();
        }
        if (reader.readBool()) {
            result.repair = RepairProperties.readFrom(reader);
        } else {
            result.repair.nullify();
        }
        return result;
    }
    void writeTo(Stream writer) const {
        writer.write(size);
        writer.write(buildScore);
        writer.write(destroyScore);
        writer.write(canMove);
        writer.write(populationProvide);
        writer.write(populationUse);
        writer.write(maxHealth);
        writer.write(cost);
        writer.write(sightRange);
        writer.write(resourcePerHealth);
        if (build.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            build.get.writeTo(writer);
        }
        if (attack.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            attack.get.writeTo(writer);
        }
        if (repair.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            repair.get.writeTo(writer);
        }
    }
    string toString() const {
        return "EntityProperties" ~ "(" ~
            to!string(size) ~
            to!string(buildScore) ~
            to!string(destroyScore) ~
            to!string(canMove) ~
            to!string(populationProvide) ~
            to!string(populationUse) ~
            to!string(maxHealth) ~
            to!string(cost) ~
            to!string(sightRange) ~
            to!string(resourcePerHealth) ~
            to!string(build) ~
            to!string(attack) ~
            to!string(repair) ~
            ")";
    }
}
