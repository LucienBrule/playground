import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication {
    SwingPanel(
        factory = { JFXWebView("http://localhost")},
        modifier = Modifier.fillMaxSize(),
    )
}
