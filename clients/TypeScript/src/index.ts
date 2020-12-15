import {Socket} from 'net';

import {DebugInterface} from './DebugInterface';
import {MyStrategy} from './MyStrategy';
import {StreamWrapper} from './StreamWrapper';
import {ServerMessage} from './model/ServerMessage';
import {ClientMessage} from './model/ClientMessage';

class Runner {
    private socket = new Socket({ readable: true, writable: true });
    private streamWrapper = new StreamWrapper(this.socket)

    constructor(
        private host: string,
        private port: number,
        private token: string,
    ) {
        this.socket
            .setNoDelay(true)
            .on('error', (error) => {
                console.error('Socket error: ' + error.message);
                process.exit(1);
            });
    }

    async connect() {
        await new Promise(resolve => {
            this.socket.connect({
                host: this.host,
                port: this.port
            }, function () {
                resolve();
            });
        });
        await this.streamWrapper.writeString(this.token);
    }

    async run() {
        try {
            await this.connect();
            const strategy = new MyStrategy();
            const debugInterface = new DebugInterface(this.streamWrapper);
            while (true) {
                const message = await ServerMessage.readFrom(this.streamWrapper);
                if (message instanceof ServerMessage.GetAction) {
                    await (new ClientMessage.ActionMessage(
                        await strategy.getAction(message.playerView, message.debugAvailable ? debugInterface : null)
                    ).writeTo(this.streamWrapper));
                    // TODO: only flush stream once here?
                } else if (message instanceof ServerMessage.Finish) {
                    break;
                } else if (message instanceof ServerMessage.DebugUpdate) {
                    await strategy.debugUpdate(message.playerView, debugInterface);
                    await (new ClientMessage.DebugUpdateDone().writeTo(this.streamWrapper));
                    // TODO: only flush stream once here?
                } else {
                    throw new Error('Unexpected server message');
                }
            }
        } catch (e) {
            console.error(e);
            process.exit(1);
        }
    }
}

const argv = process.argv;
const host = argv.length < 3 ? '127.0.0.1' : argv[2];
const port = argv.length < 4 ? 31001 : parseInt(argv[3]);
const token = argv.length < 5 ? '0000000000000000' : argv[4];

(new Runner(host, port, token)).run();
