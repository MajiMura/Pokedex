package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Stats(
    @Json(name = "base_stat")
    var baseStat: Int? = null,
    @Json(name = "effort")
    var effort: Int? = null,
    @Json(name = "stat")
    var stat: Stat? = Stat()
)