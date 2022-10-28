package io.brule.playground.client.lib.api

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket


/*
* The CursorApi is responsible for sending and receiving cursor updates from the server.
*/

@Serializable
data class CursorPosition(var x: Int, var y: Int)

@Serializable
data class CursorUpdate(val id: String, val position: CursorPosition)

class CursorApi {

    private val randomId: String
    private val ws: WebSocket
    var position = CursorPosition(0, 0)


    init {
        console.log("Cursor API init")

        randomId = genRandomId()
        ws = WebSocket("ws://${window.location.host}/api/cursors/ws/$randomId")

        ws.onopen = {
            console.log("WS opened")
            console.log(it)
        }
        ws.onmessage = {
            console.log("WS message")
            console.log(it)
            handleWSMessage(it)
        }
        ws.onerror = {
            console.log("WS error")
            console.log(it)
        }
        ws.onclose = {
            console.log("WS closed")
            console.log(it)
        }

        document.addEventListener("mousemove", { event ->
            val x: Int = event.asDynamic().clientX as Int
            val y: Int = event.asDynamic().clientY as Int
            onMouseMove(x, y)
        })
    }

    companion object {
        private val instance = CursorApi()
        fun getInstance() = instance
    }

    private fun onMouseMove(x: Int, y: Int) {
//        console.log("Mouse Moved" , x, y)
        position.apply {
            this.x = x
            this.y = y
        }
    }

    private fun periodic() {
        console.log("periodic")

        when (ws.readyState) {
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

    private fun sendPosition() {
        val json = Json.encodeToString(
            CursorUpdate.serializer(),
            CursorUpdate(randomId, position)
        )
        ws.send(json)
    }

    private fun genRandomId(): String {
        return (0..10).map { ('a'..'z').random() }.joinToString("")
    }

    private fun handleWSMessage(event: MessageEvent) {
        val json: JsonObject =
            Json.parseToJsonElement(event.data.toString()).jsonObject

        when (json["type"].toString()) {
            "cursor" -> {
                val cursorUpdate = Json.decodeFromString(
                    CursorUpdate.serializer(),
                    json["data"].toString()
                )
                console.log(cursorUpdate)
            }

            else -> {
                console.log("unhandled message type")
            }
        }
    }

}