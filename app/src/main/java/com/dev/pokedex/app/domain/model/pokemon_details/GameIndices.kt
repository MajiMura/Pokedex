package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GameIndices(
    @Json(name = "game_index")
    var gameIndex: Int? = null,
    @Json(name = "version")
    var version: Version? = Version()
)