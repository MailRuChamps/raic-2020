export interface Stream {
    readBool: () => Promise<boolean>;
    readFloat: () => Promise<number>;
    readInt: () => Promise<number>;
    readString: () => Promise<string>;

    writeBool: (value: boolean) => Promise<boolean | void>;
    writeFloat: (value: number) => Promise<boolean | void>;
    writeInt: (value: number) => Promise<boolean | void>;
    writeString: (value: string) => Promise<boolean | void>;
}

