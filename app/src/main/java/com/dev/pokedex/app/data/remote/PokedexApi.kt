package com.dev.pokedex.app.data.remote

import com.dev.pokedex.app.domain.model.responses.PokemonDetailsResponse
import com.dev.pokedex.app.domain.model.responses.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String?): Response<PokemonDetailsResponse>
}