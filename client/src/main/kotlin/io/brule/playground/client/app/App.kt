package io.brule.playground.client.app

import io.brule.playground.client.lib.api.MockAPI
import io.brule.playground.client.lib.components.View
import io.brule.playground.client.views.*
import react.FC
import react.Key
import react.Props

external interface AppProps : Props {
    var name: String
}

val AppComponent = FC<AppProps> {



    val appViews = listOf(
        HomeView.create("/", "Home"),
        CursorsView.create("/cursors", "Cursors"),
        AboutView.create("/about", "About"),
        DebugView.create("/debug", "Debug")
    )


    AppRouter {
        views = appViews
    }
}


class AppView(
    override val path: String = "/",
    override val label: String = "App",
    override var props: AppProps? = null
) : View<AppProps>(AppComponent, props)
