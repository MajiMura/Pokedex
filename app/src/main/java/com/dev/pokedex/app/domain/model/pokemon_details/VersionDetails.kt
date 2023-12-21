package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class VersionDetails(
    @Json(name = "rarity")
    var rarity: Int? = null,
    @Json(name = "version")
    var version: Version? = Version()
)