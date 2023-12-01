package com.dev.pokedex.app.domain.repository

import com.dev.pokedex.app.domain.model.PokemonResponse

interface PokedexRepository {
    suspend fun getPokemon(): PokemonResponse
}