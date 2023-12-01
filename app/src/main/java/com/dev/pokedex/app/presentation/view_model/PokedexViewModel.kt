package com.dev.pokedex.app.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.pokedex.app.domain.model.PokemonResponse
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PokemonListState {
    object Loading : PokemonListState()
    data class Success(val data: PokemonResponse) : PokemonListState()
    data class Error(val error: String) : PokemonListState()
}

@HiltViewModel
class PokedexViewModel @Inject constructor (
    private val repository: Lazy<PokedexRepository>
): ViewModel() {
    // Define a StateFlow to emit the state changes
    private val _pokemonListState: MutableStateFlow<PokemonListState> = MutableStateFlow(PokemonListState.Loading)
    val pokemonList: StateFlow<PokemonListState> get() = _pokemonListState

    init {
        // Fetch the Pokemon list when the ViewModel is created
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                // Set Loading state
                _pokemonListState.value = PokemonListState.Loading

                // Get the Pokemon list from the repository
                val result = repository.get().getPokemon()

                // Set Success state with the data
                _pokemonListState.value = PokemonListState.Success(result)
            } catch (e: Exception) {
                // Set Error state with the exception
                _pokemonListState.value = PokemonListState.Error(e.message ?: "Unknown error")
            }
        }
    }
}