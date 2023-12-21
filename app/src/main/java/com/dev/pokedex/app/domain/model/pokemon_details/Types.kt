package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Types(
    @Json(name = "slot")
    var slot: Int? = null,
    @Json(name = "type")
    var type: Type? = Type()
)