package io.brule.playground.client.lib.components


import io.brule.playground.client.app.RoutableItem
import react.FC
import react.Props

/*
    * This is a generic interface for a view component.
    * It is used to define the type of the view component
    * that is passed to the RouteItem class.
 */


external interface IViewProps<T> where T : IViewProps<T>

interface IView<T> : RoutableItem<T> where T : IViewProps<T>, T : Props {
}

abstract class View<T>(override var element: FC<T>) :
    IView<T> where T : IViewProps<T>, T : Props {
    override var path: String = ""
    override var label: String
        get() = ""
        set(value) {
            label = value
        }

}