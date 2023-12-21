package com.dev.pokedex.app.data.repository

import com.dev.pokedex.app.data.remote.PokedexApi
import com.dev.pokedex.app.domain.model.responses.PokemonDetailsResponse
import com.dev.pokedex.app.domain.model.responses.PokemonResponse
import com.dev.pokedex.app.domain.repository.PokedexRepository
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor (
    private val api: PokedexApi
) : PokedexRepository  {

    override suspend fun getPokemon(offset: Int, limit: Int): PokemonResponse {
        // Make the network request using the PokedexApi
        val response = api.getPokemon(offset, limit)

        if (response.isSuccessful) {
            // API call was successful, return the data
            return response.body() ?: throw Exception("Response body is null")
        } else {
            // API call failed, handle the error (throw an exception or return a default value)
            throw Exception("Error: ${response.code()}, ${response.message()}")
        }
    }

    override suspend fun getPokemonDetails(name: String?): PokemonDetailsResponse {
        val response = api.getPokemonDetails(name)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Response body is null")
        } else {
            throw Exception("Error: ${response.code()}, ${response.message()}")
        }
    }
}