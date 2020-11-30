class AttackProperties {
    constructor(attackRange, damage, collectResource) {
        this.attackRange = attackRange;
        this.damage = damage;
        this.collectResource = collectResource;
    }
    static async readFrom(stream) {
        let attackRange;
        attackRange = await stream.readInt();
        let damage;
        damage = await stream.readInt();
        let collectResource;
        collectResource = await stream.readBool();
        return new AttackProperties(attackRange, damage, collectResource);
    }
    async writeTo(stream) {
        let attackRange = this.attackRange;
        await stream.writeInt(attackRange);
        let damage = this.damage;
        await stream.writeInt(damage);
        let collectResource = this.collectResource;
        await stream.writeBool(collectResource);
    }
}
module.exports = AttackProperties
