package io.brule.playground.client

import io.brule.playground.client.app.AppProps
import io.brule.playground.client.app.AppView
import kotlinx.browser.document
import org.w3c.dom.Element
import react.Key
import react.create
import react.dom.client.createRoot


fun main() {

    console.log("Entrypoint")
    val container: Element = document.getElementById("root")!!


    val appProps = object : AppProps {
        override var key: Key? = "App"
        override var name: String = "App"
    }
    val appView = AppView(
        path = "/",
        label = "App",
        props = appProps
    )
    createRoot(container).render(appView.component.create())
}
