package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Moves(
    @Json(name = "move")
    var move: Move? = Move(),
    @Json(name = "version_group_details")
    var versionGroupDetails:
    List<VersionGroupDetails> = listOf()
)