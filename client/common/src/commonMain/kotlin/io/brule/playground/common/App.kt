package io.brule.playground.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val platformName = getPlatformName()



    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column {
            Text(text = text, color = Color.White)

            Button(onClick = { text = "Hello, Desktop!" }) {
                Text(text)
            }

        }

    }
}
