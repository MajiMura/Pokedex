package com.dev.pokedex.app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    var results: List<Pokemon>
)
/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 */
@Serializable
data class Pokemon(
    val name : String,
    @SerialName(value = "url")
    val url: String
)