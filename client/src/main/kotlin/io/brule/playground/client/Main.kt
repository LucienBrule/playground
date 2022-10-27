package io.brule.playground.client

import io.brule.playground.client.app.AppView
import kotlinx.browser.document
import org.w3c.dom.Element
import react.create
import react.dom.client.createRoot


fun main() {
    val container: Element = document.getElementById("root")!!
    val app = AppView("/", "Playground").component.create {
        name = "Playground"
    }

    createRoot(container).render(app)
}
