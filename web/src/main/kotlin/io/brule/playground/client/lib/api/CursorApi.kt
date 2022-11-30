package io.brule.playground.client.lib.api

import io.brule.playground.lib.CursorPosition
import io.brule.playground.lib.CursorUpdate

import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket
import org.w3c.dom.events.Event

class CursorApi {

    private val ws: WebSocket
    private val id: String = genRandomColor()
    private val listeners = mutableListOf<(CursorUpdate) -> Unit>()
    private val cursors = mutableMapOf<String, CursorUpdate>()

    init {
        ws = WebSocket("ws://${window.location.host}/api/cursors/ws/${id}")
        ws.onmessage = { this.onMessage(it) }
        ws.onopen = { this.onOpen(it) }
        ws.onclose = { this.onClose(it) }
        ws.onerror = { this.onError(it) }

        listeners.add { update ->
            cursors[update.id] = update
        }
    }

    companion object {
        private var instance: CursorApi? = null

        fun getInstance(): CursorApi {
            if (instance == null) {
                instance = CursorApi()
            }
            return instance!!
        }
    }


    private fun onOpen(event: Event) {
        console.log("onOpen")
    }

    private fun onClose(event: Event) {
        console.log("onClose")
    }

    private fun onError(event: Event) {
        console.log("onError")
    }

    private fun onMessage(event: MessageEvent) {
        val update = Json.decodeFromString(
            CursorUpdate.serializer(),
            event.data.toString()
        )
        listeners.forEach { it(update) }
    }

    private fun genRandomId(): String {
        return (0..10).map { ('a'..'z').random() }.joinToString("")
    }

    private fun genRandomColor(): String {
        val hex = (0..0xFFFFFF).random()
        return hex.toString(16).padStart(6, '0')
    }

    fun updateCursor(cursorPosition: CursorPosition) {
        if (ws.readyState == WebSocket.OPEN) {
            ws.send(
                Json.encodeToString(
                    CursorUpdate.serializer(),
                    CursorUpdate(id, cursorPosition)
                )
            )
        }
    }

    fun getCursors(): List<CursorUpdate> {
        return cursors.values.toList()
    }

    fun addListener(listener: (CursorUpdate) -> Unit) {
        listeners.add(listener)
    }
}