package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Other(
    @Json(name = "dream_world")
    var dreamWorld: DreamWorld? = DreamWorld(),
    @Json(name = "home")
    var home: Home? = Home(),
    @Json(name = "official-artwork")
    var officialArtwork: OfficialArtwork? = null
)