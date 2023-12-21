package com.dev.pokedex.app.domain.model.responses

import com.dev.pokedex.app.domain.model.pokemon_details.Abilities
import com.dev.pokedex.app.domain.model.pokemon_details.Forms
import com.dev.pokedex.app.domain.model.pokemon_details.GameIndices
import com.dev.pokedex.app.domain.model.pokemon_details.HeldItems
import com.dev.pokedex.app.domain.model.pokemon_details.Moves
import com.dev.pokedex.app.domain.model.pokemon_details.PokemonDetails
import com.dev.pokedex.app.domain.model.pokemon_details.Species
import com.dev.pokedex.app.domain.model.pokemon_details.Sprites
import com.dev.pokedex.app.domain.model.pokemon_details.Stats
import com.dev.pokedex.app.domain.model.pokemon_details.Types
import com.squareup.moshi.Json

data class PokemonDetailsResponse(
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

fun PokemonDetailsResponse.toDomainModel(): PokemonDetails {
    return PokemonDetails(
        abilities = abilities,
        baseExperience = baseExperience,
        forms = forms,
        gameIndices = gameIndices,
        height = height,
        heldItems = heldItems,
        id = id,
        isDefault = isDefault,
        locationAreaEncounters = locationAreaEncounters,
        moves = moves,
        name = name,
        order = order,
        species = species,
        sprites = sprites,
        stats = stats,
        types = types,
        weight = weight
    )
}