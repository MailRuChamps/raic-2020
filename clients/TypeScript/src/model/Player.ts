import {Stream} from './Stream';

export class Player {
    constructor(
        public id: number,
        public score: number, 
        public resource: number,
    ) {}

    static async readFrom(stream: Stream): Promise<Player> {
        const id = await stream.readInt();
        const score  = await stream.readInt();
        const resource = await stream.readInt();

        return new Player(id, score, resource);
    }

    async writeTo(stream: Stream) {
        await stream.writeInt(this.id);
        await stream.writeInt(this.score);
        await stream.writeInt(this.resource);
    }
}
