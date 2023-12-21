package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class OfficialArtwork(
    @Json(name = "front_default")
    var frontDefault: String? = null,
    @Json(name = "front_shiny")
    var frontShiny: String? = null,
)