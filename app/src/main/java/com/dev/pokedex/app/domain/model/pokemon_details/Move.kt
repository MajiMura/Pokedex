package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Move(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "url")
    var url: String? = null
)