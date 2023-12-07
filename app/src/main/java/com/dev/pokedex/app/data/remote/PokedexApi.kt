package com.dev.pokedex.app.data.remote

import com.dev.pokedex.app.domain.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonResponse>
}