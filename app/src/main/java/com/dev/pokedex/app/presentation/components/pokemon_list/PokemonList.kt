package com.dev.pokedex.app.presentation.components.pokemon_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.pokedex.app.domain.model.Pokemon

@Composable
fun PokemonList(listOfPokemon: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyColumn {
        itemsIndexed(items = listOfPokemon) { _, item ->
            PokemonListItem(pokemon = item)
        }
    }
}