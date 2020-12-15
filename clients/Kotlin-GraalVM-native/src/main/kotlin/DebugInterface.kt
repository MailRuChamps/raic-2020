import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DebugInterface(private val inputStream: InputStream, private val outputStream: OutputStream) {
    fun send(command: model.DebugCommand) {
        try {
            model.ClientMessage.DebugMessage(command).writeTo(outputStream)
            outputStream.flush()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
    fun getState(): model.DebugState {
        try {
            model.ClientMessage.RequestDebugState().writeTo(outputStream)
            outputStream.flush()
            return model.DebugState.readFrom(inputStream)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
