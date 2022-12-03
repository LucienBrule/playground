package io.brule.playground.android

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate


class MainActivity : ReactActivity() {
    override fun getMainComponentName(): String = "Playground"
    override fun createReactActivityDelegate() = ReactActivityDelegate(this, mainComponentName)
}
