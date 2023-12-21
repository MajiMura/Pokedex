package com.dev.pokedex.app.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.pokedex.app.domain.model.Pokemon
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val allPokemon: List<Pokemon>) : PokemonListState()
    data class Error(val error: String) : PokemonListState()
}
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Lazy<PokedexRepository>
) : ViewModel() {

    private val _pokemonListState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.Loading)
    val pokemonList: StateFlow<PokemonListState> get() = _pokemonListState

    private var offset: Int = 0
    private val limit: Int = 20
    private var allPokemon: MutableList<Pokemon> = mutableListOf()
    private var searchText: String = ""

    init {
        // Fetch the Pokemon list when the ViewModel is created
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                // Set Loading state only if the list is empty
                if (allPokemon.isEmpty()) {
                    _pokemonListState.value = PokemonListState.Loading
                }
                // Fetch the Pokemon list with the current offset and limit
                val result = repository.get().getPokemon(offset, limit)

                // Update the offset for the next pagination
                offset += limit

                // Update the cumulative list
                allPokemon.addAll(result.results)

                // Apply search if there is a search query
                updateFilteredPokemon()

            } catch (e: Exception) {
                // Set Error state with the exception
                _pokemonListState.value = PokemonListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // Function to load more Pokemon when the user reaches the end of the list
    fun loadMore() {
        if (pokemonList.value !is PokemonListState.Loading) {
            fetchPokemonList()
        }
    }

    fun searchPokemon(text: String) {
        searchText = text
        updateFilteredPokemon()
    }


    private fun updateFilteredPokemon() {
        // Update the filtered list based on the search text
        val filteredPokemon = if (searchText.isNotBlank()) {
            allPokemon.filter { x -> x.name.contains(searchText, ignoreCase = true) }
        } else {
            allPokemon
        }
        _pokemonListState.value = PokemonListState.Success(filteredPokemon.toList())
    }
}