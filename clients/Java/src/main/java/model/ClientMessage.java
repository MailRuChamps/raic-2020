package model;

import util.StreamUtil;

public abstract class ClientMessage {
    public abstract void writeTo(java.io.OutputStream stream) throws java.io.IOException;
    public static ClientMessage readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
            case DebugMessage.TAG:
                return DebugMessage.readFrom(stream);
            case ActionMessage.TAG:
                return ActionMessage.readFrom(stream);
            case DebugUpdateDone.TAG:
                return DebugUpdateDone.readFrom(stream);
            case RequestDebugState.TAG:
                return RequestDebugState.readFrom(stream);
            default:
                throw new java.io.IOException("Unexpected tag value");
        }
    }

    public static class DebugMessage extends ClientMessage {
        public static final int TAG = 0;
        private model.DebugCommand command;
        public model.DebugCommand getCommand() { return command; }
        public void setCommand(model.DebugCommand command) { this.command = command; }
        public DebugMessage() {}
        public DebugMessage(model.DebugCommand command) {
            this.command = command;
        }
        public static DebugMessage readFrom(java.io.InputStream stream) throws java.io.IOException {
            DebugMessage result = new DebugMessage();
            result.command = model.DebugCommand.readFrom(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            command.writeTo(stream);
        }
    }

    public static class ActionMessage extends ClientMessage {
        public static final int TAG = 1;
        private model.Action action;
        public model.Action getAction() { return action; }
        public void setAction(model.Action action) { this.action = action; }
        public ActionMessage() {}
        public ActionMessage(model.Action action) {
            this.action = action;
        }
        public static ActionMessage readFrom(java.io.InputStream stream) throws java.io.IOException {
            ActionMessage result = new ActionMessage();
            result.action = model.Action.readFrom(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            action.writeTo(stream);
        }
    }

    public static class DebugUpdateDone extends ClientMessage {
        public static final int TAG = 2;
        public DebugUpdateDone() {}
        public static DebugUpdateDone readFrom(java.io.InputStream stream) throws java.io.IOException {
            DebugUpdateDone result = new DebugUpdateDone();
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    }

    public static class RequestDebugState extends ClientMessage {
        public static final int TAG = 3;
        public RequestDebugState() {}
        public static RequestDebugState readFrom(java.io.InputStream stream) throws java.io.IOException {
            RequestDebugState result = new RequestDebugState();
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    }
}
