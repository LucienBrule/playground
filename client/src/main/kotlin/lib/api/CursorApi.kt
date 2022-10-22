import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.js.timers.setInterval
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.w3c.dom.WebSocket
import kotlin.time.Duration
import kotlin.time.ExperimentalTime


@Serializable
data class CursorPosition(val x: Int, val y: Int)

@OptIn(ExperimentalTime::class)
class CursorApi {


    private val randomId: String;
    private val ws: WebSocket;
    private var position = CursorPosition(0, 0)

    init{

        randomId = "forty-two"
        ws = WebSocket("ws://${window.location.host}/api/cursors/ws/$randomId")

        ws.onopen = {
            console.log("WS opened")
            console.log(it)
        }
        ws.onmessage = {
            console.log("WS message")
            console.log(it)
        }
        ws.onerror = {
            console.log("WS error")
            console.log(it)
        }
        ws.onclose = {
            console.log("WS closed")
            console.log(it)
        }

        setInterval( delay = Duration.seconds(5)){
            periodic()
        }

        document.addEventListener("mousemove", { event ->
            val x: Int = event.asDynamic().clientX as Int
            val y:Int = event.asDynamic().clientY as Int
            onMouseMove(x, y);
        })
    }

    private fun onMouseMove(x: Int, y: Int){
        position = CursorPosition(x, y)
    }

    private fun periodic(){
        console.log("periodic")

        when(ws.readyState){
            WebSocket.OPEN -> {
                console.log("sending")
                sendPosition()
            }
            WebSocket.CONNECTING -> {
                console.log("connecting")
            }
            WebSocket.CLOSING -> {
                console.log("closing")
            }
            WebSocket.CLOSED -> {
                console.log("closed")
            }
            else -> {
                console.log("unhandled websocket state")
            }
        }
    }

    private fun sendPosition(){
        val json = Json.encodeToString(CursorPosition.serializer(), position)
        ws.send(json)
    }
}