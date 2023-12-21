package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class HeldItems(
    @Json(name = "item")
    var item: Item? = Item(),
    @Json(name = "version_details")
    var versionDetails: List<VersionDetails> = listOf()
)