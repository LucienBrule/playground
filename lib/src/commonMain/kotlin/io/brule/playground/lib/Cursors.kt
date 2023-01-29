package io.brule.playground.lib

import kotlinx.serialization.Serializable

@Serializable
data class CursorPosition(var x: Int, var y: Int)

@Serializable
data class CursorUpdate(val id: String, val position: CursorPosition)
