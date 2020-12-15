package model;

import util.StreamUtil;

public abstract class ServerMessage {
    public abstract void writeTo(java.io.OutputStream stream) throws java.io.IOException;
    public static ServerMessage readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
            case GetAction.TAG:
                return GetAction.readFrom(stream);
            case Finish.TAG:
                return Finish.readFrom(stream);
            case DebugUpdate.TAG:
                return DebugUpdate.readFrom(stream);
            default:
                throw new java.io.IOException("Unexpected tag value");
        }
    }

    public static class GetAction extends ServerMessage {
        public static final int TAG = 0;
        private model.PlayerView playerView;
        public model.PlayerView getPlayerView() { return playerView; }
        public void setPlayerView(model.PlayerView playerView) { this.playerView = playerView; }
        private boolean debugAvailable;
        public boolean isDebugAvailable() { return debugAvailable; }
        public void setDebugAvailable(boolean debugAvailable) { this.debugAvailable = debugAvailable; }
        public GetAction() {}
        public GetAction(model.PlayerView playerView, boolean debugAvailable) {
            this.playerView = playerView;
            this.debugAvailable = debugAvailable;
        }
        public static GetAction readFrom(java.io.InputStream stream) throws java.io.IOException {
            GetAction result = new GetAction();
            result.playerView = model.PlayerView.readFrom(stream);
            result.debugAvailable = StreamUtil.readBoolean(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            playerView.writeTo(stream);
            StreamUtil.writeBoolean(stream, debugAvailable);
        }
    }

    public static class Finish extends ServerMessage {
        public static final int TAG = 1;
        public Finish() {}
        public static Finish readFrom(java.io.InputStream stream) throws java.io.IOException {
            Finish result = new Finish();
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    }

    public static class DebugUpdate extends ServerMessage {
        public static final int TAG = 2;
        private model.PlayerView playerView;
        public model.PlayerView getPlayerView() { return playerView; }
        public void setPlayerView(model.PlayerView playerView) { this.playerView = playerView; }
        public DebugUpdate() {}
        public DebugUpdate(model.PlayerView playerView) {
            this.playerView = playerView;
        }
        public static DebugUpdate readFrom(java.io.InputStream stream) throws java.io.IOException {
            DebugUpdate result = new DebugUpdate();
            result.playerView = model.PlayerView.readFrom(stream);
            return result;
        }
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            playerView.writeTo(stream);
        }
    }
}
