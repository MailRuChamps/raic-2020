import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedOutputStream;

import util.StreamUtil;

public class Runner {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    Runner(String host, int port, String token) throws IOException {
        Socket socket = new Socket(host, port);
        socket.setTcpNoDelay(true);
        inputStream = new BufferedInputStream(socket.getInputStream());
        outputStream = new BufferedOutputStream(socket.getOutputStream());
        StreamUtil.writeString(outputStream, token);
        outputStream.flush();
    }

    void run() throws IOException {
        MyStrategy myStrategy = new MyStrategy();
        DebugInterface debugInterface = new DebugInterface(inputStream, outputStream);
        while (true) {
            model.ServerMessage message = model.ServerMessage.readFrom(inputStream);
            if (message instanceof model.ServerMessage.GetAction) {
                model.ServerMessage.GetAction getActionMessage = (model.ServerMessage.GetAction) message;
                new model.ClientMessage.ActionMessage(myStrategy.getAction(getActionMessage.getPlayerView(), getActionMessage.isDebugAvailable() ? debugInterface : null)).writeTo(outputStream);
                outputStream.flush();
            } else if (message instanceof model.ServerMessage.Finish) {
                break;
            } else if (message instanceof model.ServerMessage.DebugUpdate) {
                model.ServerMessage.DebugUpdate debugUpdateMessage = (model.ServerMessage.DebugUpdate) message;
                myStrategy.debugUpdate(debugUpdateMessage.getPlayerView(), debugInterface);
                new model.ClientMessage.DebugUpdateDone().writeTo(outputStream);
                outputStream.flush();
            } else {
                throw new IOException("Unexpected server message");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String host = args.length < 1 ? "127.0.0.1" : args[0];
        int port = args.length < 2 ? 31001 : Integer.parseInt(args[1]);
        String token = args.length < 3 ? "0000000000000000" : args[2];
        new Runner(host, port, token).run();
    }
}