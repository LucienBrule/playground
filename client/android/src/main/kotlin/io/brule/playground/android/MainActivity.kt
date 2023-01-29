package io.brule.playground.android

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import io.brule.playground.common.MainView

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}
