import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import util.StreamUtil

class Runner @Throws(IOException::class)
internal constructor(host: String, port: Int, token: String) {
    private val inputStream: InputStream
    private val outputStream: OutputStream

    init {
        val socket = Socket(host, port)
        socket.tcpNoDelay = true
        inputStream = BufferedInputStream(socket.getInputStream())
        outputStream = BufferedOutputStream(socket.getOutputStream())
        StreamUtil.writeString(outputStream, token)
        outputStream.flush()
    }

    @Throws(IOException::class)
    internal fun run() {
        val myStrategy = MyStrategy()
        val debugInterface = DebugInterface(inputStream, outputStream)
        while (true) {
            val message = model.ServerMessage.readFrom(inputStream)
            if (message is model.ServerMessage.GetAction) {
                model.ClientMessage.ActionMessage(myStrategy.getAction(message.playerView, if (message.debugAvailable) debugInterface else null)).writeTo(outputStream)
                outputStream.flush()
            } else if (message is model.ServerMessage.Finish) {
                break
            } else if (message is model.ServerMessage.DebugUpdate) {
                myStrategy.debugUpdate(message.playerView, debugInterface)
                model.ClientMessage.DebugUpdateDone().writeTo(outputStream)
                outputStream.flush()
            }
        }
    }

    companion object {

        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val host = if (args.size < 1) "127.0.0.1" else args[0]
            val port = if (args.size < 2) 31001 else Integer.parseInt(args[1])
            val token = if (args.size < 3) "0000000000000000" else args[2]
            Runner(host, port, token).run()
        }
    }
}
