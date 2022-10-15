import csstype.*
import emotion.css.css
import emotion.react.css
import react.FC
import react.Props
import react.create
import react.createElement
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.u
import react.dom.html.ReactHTML.ul
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.Link

external interface RouterProps: Props{

}

val Router = FC<RouterProps> {
    BrowserRouter{
        div{
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                fontFamily = FontFamily.monospace
                justifyContent = JustifyContent.normal
                lineHeight = 4.em
                paddingLeft = 1.em
                "div"{
                    width = 10.ch
                    fontSize = 1.em
                }
            }
            div {
                Link {
                    to = "/"
                    +"Home"

                }
            }
            div {
                Link {
                    to = "/about"
                    +"About"
                }
            }
        }

        Routes{
            Route{
                path = "/"
                element = Home.create{
                    placeholder = "..."
                    label = "Submit"
                }
            }
            Route{
                path = "/about"
                element = createElement(About)
            }
        }
    }
}