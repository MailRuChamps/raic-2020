import * as os from 'os';
import {Socket} from 'net';
import {Stream} from './model/Stream';

const BOOL_SIZE = 1;
const INT_SIZE = 4;
const LONG_SIZE = 8;
const FLOAT_SIZE = 4;
const DOUBLE_SIZE = 8;

export class StreamWrapper implements Stream {
    private socket: Socket;
    private data: Buffer;
    private needAmount: number | null;
    private resolve: any | null;
    private isLittleEndianMachine: boolean;

    constructor(socket: Socket) {
        this.socket = socket;
        this.data = Buffer.alloc(0);
        this.needAmount = null;
        this.resolve = null;
        this.socket.on('data', data => this.dataHandler(data));
        this.isLittleEndianMachine = (os.endianness() === 'LE');
    }

    dataHandler(data: Buffer) {
        this.data = Buffer.concat([this.data, data]);
        this.update();
    }

    update() {
        if (this.needAmount === null || this.needAmount > this.data.length) {
            return;
        }
        const data = this.data.slice(0, this.needAmount);
        this.data = this.data.slice(this.needAmount);
        this.needAmount = null;
        this.resolve(data);
        this.update();
    }

    close() {
        this.socket.destroy();
    }

    // Reading primitives

    private _read(size: number): Promise<Buffer> {
        return new Promise<Buffer>((resolve) => {
            this.needAmount = size;
            this.resolve = resolve;
            this.update();
        }).catch(error => {
            throw new Error('Error while reading data: ' + error.message)
        });
    }

    async readBool() {
        const buffer = await this._read(BOOL_SIZE);
        return !!buffer.readInt8();
    }

    async readInt() {
        const buffer = await this._read(INT_SIZE);
        if (this.isLittleEndianMachine) {
            // @ts-ignore
            return buffer.readInt32LE(0, INT_SIZE);
        }
        // @ts-ignore
        return buffer.readInt32BE(0, INT_SIZE);
    }

    async readLong() {
        const buffer = await this._read(LONG_SIZE);
        if (this.isLittleEndianMachine) {
            // @ts-ignore
            return parseInt(buffer.readBigInt64LE());
        }
        // @ts-ignore
        return parseInt(buffer.readBigInt64BE());
    }

    async readFloat() {
        const buffer = await this._read(FLOAT_SIZE);
        if (this.isLittleEndianMachine) {
            return buffer.readFloatLE();
        }
        return buffer.readFloatBE();
    }

    async readDouble() {
        const buffer = await this._read(DOUBLE_SIZE);
        if (this.isLittleEndianMachine) {
            return buffer.readDoubleLE();
        }
        return buffer.readDoubleBE();
    }

    async readString() {
        const length = await this.readInt();
        const buffer = await this._read(length);
        const result = buffer.toString();
        if (result.length !== length) {
            throw new Error('Unexpected EOF');
        }
        return result;
    }

    // Writing primitives

    _write(data: Buffer) {
        const socket = this.socket;
        return new Promise<boolean | undefined>(function (resolve, reject) {
            socket.write(data, 'utf8', function (error) {
                if (error) {
                    return reject(error);
                }
                resolve(true);
            });
        }).catch(function (error) {
            console.log('Error while writing data ' + error.message);
        });
    }

    async writeBool(value: boolean) {
        const buffer = Buffer.alloc(BOOL_SIZE);
        // @ts-ignore
        buffer.writeInt8(value);
        return await this._write(buffer);
    }

    async writeInt(value: number) {
        const buffer = Buffer.alloc(INT_SIZE);
        if (this.isLittleEndianMachine) {
            buffer.writeInt32LE(value);
        } else {
            buffer.writeInt32BE(value);
        }
        return await this._write(buffer);
    }

    async writeLong(value: bigint) {
        const buffer = Buffer.alloc(LONG_SIZE);
        if (this.isLittleEndianMachine) {
            buffer.writeBigInt64LE(value);
        } else {
            buffer.writeBigInt64BE(value);
        }
        return await this._write(buffer);
    }

    async writeFloat(value: number) {
        const buffer = Buffer.alloc(FLOAT_SIZE);
        if (this.isLittleEndianMachine) {
            buffer.writeFloatLE(value);
        } else {
            buffer.writeFloatBE(value);
        }
        return await this._write(buffer);
    }

    async writeDouble(value: number) {
        const buffer = Buffer.alloc(DOUBLE_SIZE);
        if (this.isLittleEndianMachine) {
            buffer.writeDoubleLE(value);
        } else {
            buffer.writeDoubleBE(value);
        }
        return await this._write(buffer);
    }

    async writeString(value: string) {
        this.writeInt(value.length);
        // @ts-ignore
        return await this._write(value, 'utf8');
    }
}
