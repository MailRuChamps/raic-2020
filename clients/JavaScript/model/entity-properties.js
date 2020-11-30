const BuildProperties = require('./build-properties');
const AttackProperties = require('./attack-properties');
const RepairProperties = require('./repair-properties');
class EntityProperties {
    constructor(size, buildScore, destroyScore, canMove, populationProvide, populationUse, maxHealth, cost, sightRange, resourcePerHealth, build, attack, repair) {
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
    static async readFrom(stream) {
        let size;
        size = await stream.readInt();
        let buildScore;
        buildScore = await stream.readInt();
        let destroyScore;
        destroyScore = await stream.readInt();
        let canMove;
        canMove = await stream.readBool();
        let populationProvide;
        populationProvide = await stream.readInt();
        let populationUse;
        populationUse = await stream.readInt();
        let maxHealth;
        maxHealth = await stream.readInt();
        let cost;
        cost = await stream.readInt();
        let sightRange;
        sightRange = await stream.readInt();
        let resourcePerHealth;
        resourcePerHealth = await stream.readInt();
        let build;
        if (await stream.readBool()) {
            build = await BuildProperties.readFrom(stream);
        } else {
            build = null;
        }
        let attack;
        if (await stream.readBool()) {
            attack = await AttackProperties.readFrom(stream);
        } else {
            attack = null;
        }
        let repair;
        if (await stream.readBool()) {
            repair = await RepairProperties.readFrom(stream);
        } else {
            repair = null;
        }
        return new EntityProperties(size, buildScore, destroyScore, canMove, populationProvide, populationUse, maxHealth, cost, sightRange, resourcePerHealth, build, attack, repair);
    }
    async writeTo(stream) {
        let size = this.size;
        await stream.writeInt(size);
        let buildScore = this.buildScore;
        await stream.writeInt(buildScore);
        let destroyScore = this.destroyScore;
        await stream.writeInt(destroyScore);
        let canMove = this.canMove;
        await stream.writeBool(canMove);
        let populationProvide = this.populationProvide;
        await stream.writeInt(populationProvide);
        let populationUse = this.populationUse;
        await stream.writeInt(populationUse);
        let maxHealth = this.maxHealth;
        await stream.writeInt(maxHealth);
        let cost = this.cost;
        await stream.writeInt(cost);
        let sightRange = this.sightRange;
        await stream.writeInt(sightRange);
        let resourcePerHealth = this.resourcePerHealth;
        await stream.writeInt(resourcePerHealth);
        let build = this.build;
        if (build === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await build.writeTo(stream);
        }
        let attack = this.attack;
        if (attack === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await attack.writeTo(stream);
        }
        let repair = this.repair;
        if (repair === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await repair.writeTo(stream);
        }
    }
}
module.exports = EntityProperties
