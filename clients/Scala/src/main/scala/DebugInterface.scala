import java.io.InputStream
import java.io.OutputStream

class DebugInterface(private val inputStream: InputStream, private val outputStream: OutputStream) {
  def send(command: model.DebugCommand) {
    model.ClientMessage.DebugMessage(command).writeTo(outputStream)
    outputStream.flush()
  }
  def getState(): model.DebugState = {
    model.ClientMessage.RequestDebugState().writeTo(outputStream)
    outputStream.flush()
    model.DebugState.readFrom(inputStream)
  }
}