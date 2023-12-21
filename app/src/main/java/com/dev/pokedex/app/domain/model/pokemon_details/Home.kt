package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Home(
    @Json(name = "front_default")
    var frontDefault: String? = null,
    @Json(name = "front_female")
    var frontFemale: String? = null,
    @Json(name = "front_shiny")
    var frontShiny: String? = null,
    @Json(name = "front_shiny_female")
    var frontShinyFemale: String? = null
)
