import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.div
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.Link


interface RoutableItem<T> where T : Props {
    var label: String
    var path: String
    var element: FC<T>
}

data class RouteItem<T>(
    override var label: String,
    override var path: String,
    override var element: FC<T>
) :
    RoutableItem<T> where T : Props


external interface AppRouterProps : Props {
    var routeLinks: List<RoutableItem<out Props>>
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

            it.routeLinks.forEach {
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
            it.routeLinks.forEach {
                Route {
                    path = it.path
                    element = it.element.create()
                }
            }
        }
    }
}