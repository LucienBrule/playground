import kotlinx.browser.document
import org.w3c.dom.Element
import react.create
import react.dom.client.createRoot


fun main() {
    val container: Element = document.getElementById("root")!!

    val app = App.create {
        name = "Playground"
    }
    val cursor = CursorApi()

    createRoot(container).render(app)
}
