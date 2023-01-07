package io.brule.playground.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

fun genRandomColor(): Color {
    val r = (0..255).random()
    val g = (0..255).random()
    val b = (0..255).random()
    return Color(r, g, b)
}

fun Color.getHex(): String {
    return "#" + (this.toArgb() and 0xFFFFFF).toString(16).padStart(6, '0')
}

fun Color.isLight(): Boolean {
    return this.luminance() > 0.5
}

expect fun handleColorChange(color: String)

@Composable
fun App() {

    val colors = listOf<Color>(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Gray,
        Color.White,
        Color.Black
    )

    val counter: MutableState<Int> = remember { mutableStateOf(0) }
    val color = if(counter.value < 10)
        colors[counter.value % colors.size] else genRandomColor()
    handleColorChange(color.getHex())
    val text = color.getHex()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(600.dp)
                .width(360.dp)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text,
                color = color.luminance().let {
                    if (it > 0.5f) Color.Black else Color.White
                })
        }
        Button(
            onClick = {
                counter.value = counter.value + 1
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (color.isLight()) Color.Black else Color.White
            )
        ) {
            Text(
                text = "Color Me",
                color = if (color.isLight()) Color.White else Color.Black
            )
        }
    }
}



