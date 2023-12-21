package com.dev.pokedex.app.domain.repository

import com.dev.pokedex.app.domain.model.responses.PokemonDetailsResponse
import com.dev.pokedex.app.domain.model.responses.PokemonResponse

interface PokedexRepository {
    suspend fun getPokemon(offset: Int, limit: Int): PokemonResponse

    suspend fun getPokemonDetails(name: String?): PokemonDetailsResponse
}