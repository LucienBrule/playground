import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import javafx.application.Platform
import javafx.concurrent.Worker
import javafx.embed.swing.JFXPanel
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.web.WebEngine
import javafx.scene.web.WebErrorEvent
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView
import netscape.javascript.JSObject

@Suppress("SetJavaScriptEnabled")
class JFXWebView(url: String) : JFXPanel() {

    private var engine: WebEngine? = null
    private var window: JSObject? = null

    class JavaBridge {
        fun <T: JSObject>log(message: T) {
           println(message)

        }
    }

    private val bridge = JavaBridge()

    init {
        Platform.runLater(::initScene)
        load(url)
    }

    private fun initScene() {
        val webview = WebView().also {
            it.pageFill = Color.TRANSPARENT
        }
        engine = webview.engine.apply {
            isJavaScriptEnabled = true
            isDebugInspectorInfoEnabled = true
            onError = onErrorHandler
            onStatusChanged = onStatusChangedHandler
            onVisibilityChanged = onVisibilityChangedHandler
            onAlert = onAlertHandler
            loadWorker.stateProperty().addListener { _, _, newValue ->
                if (newValue == Worker.State.SUCCEEDED) {
                    println("Loaded")
                }
            }
        }
        scene = Scene(webview).also { it.fill = Color.TRANSPARENT }
    }

    fun load(url: String) {
        println("Loading $url")
        Platform.runLater {
            if (engine == null) {
                throw Exception("called load on null engine")
            }
            engine!!.load(url)
            engine!!.loadWorker.stateProperty().addListener { ov, oldState, newState ->

                println("State changed from $oldState to $newState")

                if (newState == Worker.State.SUCCEEDED) {
                    println("Loaded")
                    window = engine!!.executeScript("window") as JSObject
                    window!!.setMember("bridge", bridge)
                    engine!!.executeScript(
                        """
                            console.error = function(...message){bridge.log(message)};
                            console.info = function(...message){bridge.log(message)};
                            console.log = function(...message){bridge.log(message)};
                            console.warn = function(...message){bridge.log(message)};
                        """.trimIndent()
                    )
                }
            }



        }

    }


    companion object {
        private val onErrorHandler = EventHandler<WebErrorEvent> { event ->
            println("Error: $event")
        }

        private val onStatusChangedHandler =
            EventHandler<WebEvent<String>> { event ->
                println("Status: $event")
            }
        private val onVisibilityChangedHandler =
            EventHandler<WebEvent<Boolean>> { event ->
                println("Visibility: $event")
            }
        private val onAlertHandler = EventHandler<WebEvent<String>> { event ->
            println("Alert: $event")
        }
    }
}
