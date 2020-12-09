package model;

import util.StreamUtil;

public abstract class DebugCommand {
    public abstract void writeTo(java.io.OutputStream stream) throws java.io.IOException;
    public static DebugCommand readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
            case Add.TAG:
                return Add.readFrom(stream);
            case Clear.TAG:
                return Clear.readFrom(stream);
            case SetAutoFlush.TAG:
                return SetAutoFlush.readFrom(stream);
            case Flush.TAG:
                return Flush.readFrom(stream);
            default:
                throw new java.io.IOException("Unexpected tag value");
        }
    }

    public static class Add extends DebugCommand {
        public static final int TAG = 0;
        private model.DebugData data;
        public model.DebugData getData() { return data; }
        public void setData(model.DebugData data) { this.data = data; }
        public Add() {}
        public Add(model.DebugData data) {
            this.data = data;
        }
        public static Add readFrom(java.io.InputStream stream) throws java.io.IOException {
            Add result = new Add();
            result.data = model.DebugData.readFrom(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            data.writeTo(stream);
        }
    }

    public static class Clear extends DebugCommand {
        public static final int TAG = 1;
        public Clear() {}
        public static Clear readFrom(java.io.InputStream stream) throws java.io.IOException {
            Clear result = new Clear();
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    }

    public static class SetAutoFlush extends DebugCommand {
        public static final int TAG = 2;
        private boolean enable;
        public boolean isEnable() { return enable; }
        public void setEnable(boolean enable) { this.enable = enable; }
        public SetAutoFlush() {}
        public SetAutoFlush(boolean enable) {
            this.enable = enable;
        }
        public static SetAutoFlush readFrom(java.io.InputStream stream) throws java.io.IOException {
            SetAutoFlush result = new SetAutoFlush();
            result.enable = StreamUtil.readBoolean(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            StreamUtil.writeBoolean(stream, enable);
        }
    }

    public static class Flush extends DebugCommand {
        public static final int TAG = 3;
        public Flush() {}
        public static Flush readFrom(java.io.InputStream stream) throws java.io.IOException {
            Flush result = new Flush();
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    }
}
