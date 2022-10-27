package io.brule.playground.client.app

import csstype.*
import emotion.react.css
import io.brule.playground.client.lib.components.IView
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.div
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.Link


external interface AppRouterProps : Props {
    var views: List<IView<out Props>>
}

val AppRouter = FC<AppRouterProps> {
    BrowserRouter {
        div {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                fontFamily = FontFamily.monospace
                justifyContent = JustifyContent.normal
                lineHeight = 4.em
                paddingLeft = 1.em
                "div" {
                    width = 10.ch
                    fontSize = 1.em
                }
            }

            it.views.forEach {
                Link {
                    css {
                        color = Color("#61dafb")
                        textDecoration = TextDecoration.wavy
                        ":hover" {
                            color = Color("#61dafb")
                            textDecoration = TextDecoration.underline
                        }
                        marginRight = 1.em
                    }
                    to = it.path
                    +it.label
                }
            }
        }

        Routes {
            it.views.forEach {
                Route {
                    path = it.path
                    element = it.component.create()
                }
            }
        }
    }
}