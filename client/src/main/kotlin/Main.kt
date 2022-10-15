import kotlinx.browser.document
import org.w3c.dom.Element
import react.create
import react.dom.client.createRoot


fun main() {
    val container: Element = document.getElementById("root")!!

    val app = App.create {
        name = "Playground"
    }

    createRoot(container).render(app)
}
