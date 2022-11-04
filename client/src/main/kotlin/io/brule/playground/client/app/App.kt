package io.brule.playground.client.app

import io.brule.playground.client.lib.components.View
import io.brule.playground.client.views.AboutView
import io.brule.playground.client.views.CursorsView
import io.brule.playground.client.views.DebugView
import io.brule.playground.client.views.HomeView
import react.FC
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
