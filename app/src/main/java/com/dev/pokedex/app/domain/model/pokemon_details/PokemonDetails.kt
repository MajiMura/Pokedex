package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class PokemonDetails(
    @Json(name = "abilities")
    var abilities: List<Abilities> = listOf(),
    @Json(name = "base_experience")
    var baseExperience: Int? = null,
    @Json(name = "forms")
    var forms: List<Forms> = listOf(),
    @Json(name = "game_indices")
    var gameIndices: List<GameIndices> = listOf(),
    @Json(name = "height")
    var height: Int? = null,
    @Json(name = "held_items")
    var heldItems: List<HeldItems> = listOf(),
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "is_default")
    var isDefault: Boolean? = null,
    @Json(name = "location_area_encounters")
    var locationAreaEncounters: String? = null,
    @Json(name = "moves")
    var moves: List<Moves> = listOf(),
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "order")
    var order: Int? = null,
    @Json(name = "species")
    var species: Species? = Species(),
    @Json(name = "sprites")
    var sprites: Sprites? = Sprites(),
    @Json(name = "stats")
    var stats: List<Stats> = listOf(),
    @Json(name = "types")
    var types: List<Types> = listOf(),
    @Json(name = "weight")
    var weight: Int? = null
)