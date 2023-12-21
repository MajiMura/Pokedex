package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class Abilities(
    @Json(name = "ability")
    var ability: Ability? = Ability(),
    @Json(name = "is_hidden")
    var isHidden: Boolean? = null,
    @Json(name = "slot")
    var slot: Int? = null
)