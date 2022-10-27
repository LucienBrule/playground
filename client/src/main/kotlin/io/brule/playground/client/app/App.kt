package io.brule.playground.client.app

import io.brule.playground.client.lib.components.View
import io.brule.playground.client.views.AboutView
import io.brule.playground.client.views.DebugView
import io.brule.playground.client.views.HomeView
import react.FC
import react.Props

external interface AppProps : Props {
    var name: String
}


val AppComponent = FC<AppProps> {
    AppRouter {
        views = listOf(
            HomeView("/", "Home"),
            AboutView("/about", "About"),
            DebugView("/debug", "Debug")
        )
    }
}

class AppView(
    override val path: String,
    override val label: String
) : View<AppProps>(AppComponent) {}
