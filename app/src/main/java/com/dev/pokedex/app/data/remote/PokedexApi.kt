package com.dev.pokedex.app.data.remote

import com.dev.pokedex.app.domain.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokedexApi {

    @GET("pokemon")
    suspend fun getPokemon(): Response<PokemonResponse>
}