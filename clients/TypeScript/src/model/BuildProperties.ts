import {Stream} from './Stream';

export class BuildProperties {
    constructor(
        public options: number[],
        public initHealth: number | null
    ) {}

    static async readFrom(stream: Stream) {
        const options = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            const optionsElement = await stream.readInt();
            options.push(optionsElement);
        }

        const initHealth = await stream.readBool() ? await stream.readInt() : null;

        return new BuildProperties(options, initHealth);
    }
    async writeTo(stream: Stream) {
        let options = this.options;
        await stream.writeInt(options.length);

        for (let optionsElement of options) {
            await stream.writeInt(optionsElement);
        }

        let initHealth = this.initHealth;
        if (initHealth === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(initHealth);
        }
    }
}
