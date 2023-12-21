package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Item(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "url")
    var url: String? = null
)