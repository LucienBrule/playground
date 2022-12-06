import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.web.WebErrorEvent
import javafx.scene.web.WebEvent
import javafx.scene.web.WebView

class JFXWebView : JFXPanel() {

    private val webView = WebView()
    private val webEngine = webView.engine

    init {
        Platform.runLater {
            initScene()
        }
    }

    fun load(url: String) {
        Platform.runLater {
            webEngine.load(url)
        }
    }


    @Suppress("SetJavaScriptEnabled")
    private fun initScene(){
        scene = Scene(
            WebView().apply {
                engine.apply {
                    isJavaScriptEnabled = true
                    isDebugInspectorInfoEnabled = true
                    onError = onErrorHandler
                    onAlert = onSomethingHandler
                    onStatusChanged = onSomethingHandler
                }
            }
        )
    }

    private val onSomethingHandler = EventHandler<WebEvent<String>> { event ->
        println("Event: $event")
    }

    private val onErrorHandler = EventHandler<WebErrorEvent> { event ->
        println("Error: $event")
    }
}
