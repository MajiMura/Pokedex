package com.dev.pokedex.app.presentation.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.pokedex.app.domain.model.pokemon_details.PokemonDetails
import com.dev.pokedex.app.domain.model.responses.toDomainModel
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PokemonDetailsState {
    object Loading : PokemonDetailsState()
    data class Success(val pokemonDetails: PokemonDetails) : PokemonDetailsState()
    data class Error(val error: String) : PokemonDetailsState()
}

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: Lazy<PokedexRepository>,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val pokemonName = savedStateHandle.get<String>("pokemonName") ?: "default_value"
    private val _pokemonDetailsState: MutableStateFlow<PokemonDetailsState> =
        MutableStateFlow(PokemonDetailsState.Loading)
    val pokemonDetails: StateFlow<PokemonDetailsState> get() = _pokemonDetailsState

    init {
        fetchPokemonDetails(pokemonName)
    }

    private fun fetchPokemonDetails(name: String?) {
        viewModelScope.launch {
            try {
                val result = repository.get().getPokemonDetails(name = pokemonName)
                val pokemonDetails: PokemonDetails = result.toDomainModel()
                _pokemonDetailsState.value = PokemonDetailsState.Success(pokemonDetails)

            } catch (e: Exception) {
                // Set Error state with the exception
                _pokemonDetailsState.value = PokemonDetailsState.Error(e.message ?: "Unknown error")
            }
        }
    }
}