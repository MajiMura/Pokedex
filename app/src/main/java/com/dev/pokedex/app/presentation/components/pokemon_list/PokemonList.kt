package com.dev.pokedex.app.presentation.components.pokemon_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dev.pokedex.app.domain.model.Pokemon

@Composable
fun PokemonList(listOfPokemon: List<Pokemon>,
                scrollState: LazyListState,
                modifier: Modifier = Modifier,
                loadMore: () -> Unit) {
//    val lazyListState = remember { LazyListState() }
    LazyColumn (
        state = scrollState,
        modifier = modifier
    ) {
        itemsIndexed(items = listOfPokemon) { index, item ->
            PokemonListItem(pokemon = item)

            // When the user reaches the last item, load more data
            if (index == listOfPokemon.size - 1) {
                LaunchedEffect(scrollState) {
                    loadMore()
                }
            }
        }
    }
}