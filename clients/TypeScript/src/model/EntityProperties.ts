import {BuildProperties} from './BuildProperties';
import {AttackProperties} from './AttackProperties';
import {RepairProperties} from './RepairProperties';
import {Stream} from './Stream';

export class EntityProperties {
    constructor(
        public size: number,
        public buildScore: number,
        public destroyScore: number,
        public canMove: boolean,
        public populationProvide: number,
        public populationUse: number,
        public maxHealth: number,
        public cost: number,
        public sightRange: number,
        public resourcePerHealth: number,
        public build: BuildProperties | null,
        public attack: AttackProperties | null,
        public repair: RepairProperties | null,
    ) {}

    static async readFrom(stream: Stream) {
        const size = await stream.readInt();
        const buildScore = await stream.readInt();
        const destroyScore = await stream.readInt();
        const canMove = await stream.readBool();
        const populationProvide = await stream.readInt();
        const populationUse = await stream.readInt();
        const maxHealth = await stream.readInt();
        const cost = await stream.readInt();
        const sightRange = await stream.readInt();
        const resourcePerHealth = await stream.readInt();
        const build = await stream.readBool() ? await BuildProperties.readFrom(stream) : null;
        const attack = await stream.readBool() ? await AttackProperties.readFrom(stream) : null;
        const repair = await stream.readBool() ? await RepairProperties.readFrom(stream) : null;

        return new EntityProperties(size, buildScore, destroyScore, canMove, populationProvide, populationUse, maxHealth, cost, sightRange, resourcePerHealth, build, attack, repair);
    }
    async writeTo(stream: Stream) {
        await stream.writeInt(this.size);
        await stream.writeInt(this.buildScore);
        await stream.writeInt(this.destroyScore);
        await stream.writeBool(this.canMove);
        await stream.writeInt(this.populationProvide);
        await stream.writeInt(this.populationUse);
        await stream.writeInt(this.maxHealth);
        await stream.writeInt(this.cost);
        await stream.writeInt(this.sightRange);
        await stream.writeInt(this.resourcePerHealth);

        if (this.build === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.build.writeTo(stream);
        }

        if (this.attack === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.attack.writeTo(stream);
        }

        if (this.repair === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await this.repair.writeTo(stream);
        }
    }
}
