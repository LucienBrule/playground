package io.brule.playground.client.app

import io.brule.playground.client.views.*
import react.FC
import react.Props

external interface AppProps : Props {
    var name: String
}


val App = FC<AppProps> { props ->
    AppRouter {
        routeLinks = listOf(
            RouteItem<HomeProps>("Home", "/", Home),
            RouteItem<AboutProps>("About", "/about", About),
            RouteItem<DebugProps>("Debug", "/debug", Debug)
        )
    }
}
