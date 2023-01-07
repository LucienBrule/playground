import androidx.compose.ui.window.Window
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.HTMLCanvasElement

fun main() {

    makeCanvas("ComposeTarget")

    onWasmReady {
        Window("Playground") {
            MainView()
        }
    }
}

fun makeCanvas(id: String): HTMLCanvasElement {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.id = id
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
    document.body?.appendChild(canvas)
    return canvas
}
