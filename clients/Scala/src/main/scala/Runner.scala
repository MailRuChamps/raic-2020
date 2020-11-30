import java.io.{BufferedInputStream, BufferedOutputStream}
import java.net.Socket

import util.StreamUtil

object Runner extends App {

  val host = if (args.length < 1) "127.0.0.1" else args(0)
  val port = if (args.length < 2) 31001 else args(1).toInt
  val token = if (args.length < 3) "0000000000000000" else args(2)

  run(host, port, token)

  def run(host: String, port: Int, token: String) {
    val socket = new Socket(host, port)
    socket.setTcpNoDelay(true)
    val inputStream = new BufferedInputStream(socket.getInputStream)
    val outputStream = new BufferedOutputStream(socket.getOutputStream)

    StreamUtil.writeString(outputStream, token)
    outputStream.flush()

    val myStrategy = new MyStrategy()
    val debugInterface = new DebugInterface(inputStream, outputStream)
    while (true) {
      model.ServerMessage.readFrom(inputStream) match {
        case model.ServerMessage.GetAction(playerView, debugAvailable) =>
          model.ClientMessage.ActionMessage(myStrategy.getAction(playerView, if (debugAvailable) Some(debugInterface) else None)).writeTo(outputStream)
          outputStream.flush()
        case model.ServerMessage.Finish() => return
        case model.ServerMessage.DebugUpdate(playerView) =>
          myStrategy.debugUpdate(playerView, debugInterface)
          model.ClientMessage.DebugUpdateDone().writeTo(outputStream)
          outputStream.flush()
      }
    }
  }
}
