package io.brule.playground.client.lib.components

import io.brule.playground.client.lib.router.Routable
import kotlinx.serialization.Serializable
import react.*
import kotlin.reflect.typeOf


interface IView<T : Props> : Routable {
    val component: FC<T>
}


@Serializable
abstract class View<T>(
    final override val component: FC<T>,
) : IView<T> where T : Props {
    override val label: String = component.displayName ?: "!Un Labeled View!"
    override val path: String = "/${component.displayName?.lowercase()}"


    override fun toString(): String {
        return "View(label='$label', path='$path')"
    }


}

