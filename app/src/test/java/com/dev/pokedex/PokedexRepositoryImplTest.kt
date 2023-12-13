package com.dev.pokedex

import com.dev.pokedex.app.data.remote.PokedexApi
import com.dev.pokedex.app.data.repository.PokedexRepositoryImpl
import com.dev.pokedex.app.domain.model.Pokemon
import com.dev.pokedex.app.domain.model.PokemonResponse
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PokedexRepositoryImplTest {
    @Mock
    private lateinit var mockApi: PokedexApi

    private lateinit var repository: PokedexRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = PokedexRepositoryImpl(mockApi)
    }

    @Test
    fun getPokemonTestReturnsSuccess () = runBlocking {
        // Mock a successful response from the API
        val mockPokemon = Pokemon(name = "Bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val mockResponse = PokemonResponse(
            count = 1292,
            previous = null,
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            results = listOf(mockPokemon))
        `when`(mockApi.getPokemon(0, 1)).thenReturn(Response.success(mockResponse))

        // Call the repository function
        val result = repository.getPokemon(0, 1)

        // Verify that the result matches the expected data
        assertEquals(mockResponse, result)
    }

    @Test fun getPokemonTestReturnsError(): Unit = runBlocking {
        // Mock an error response from the API
        `when`(mockApi.getPokemon(0, 1)).thenReturn(Response.error(404,
            "".toResponseBody("text/plain".toMediaType())))

        try {
            // Call the repository function
            repository.getPokemon(0, 1)
        } catch (e: Exception) {
            // Handle the exception or perform additional assertions
            assertEquals("Error: 404, Response.error()", e.message)
        }
    }
}