package com.dev.pokedex.app.domain.model.responses

import com.dev.pokedex.app.domain.model.Pokemon
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    var results: List<Pokemon>
)