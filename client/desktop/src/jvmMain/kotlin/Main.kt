import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication

fun main() =
    singleWindowApplication {
        Box(modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
        )
        SwingPanel(
            factory = { JFXWebView("http://localhost") },
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxSize(),
        )
    }
