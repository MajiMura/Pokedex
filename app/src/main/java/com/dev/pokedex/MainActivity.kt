package com.dev.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.pokedex.app.presentation.components.pokemon_list.PokemonList
import com.dev.pokedex.app.presentation.components.pokemon_list.PokemonListItem
import com.dev.pokedex.app.presentation.view_model.PokedexViewModel
import com.dev.pokedex.app.presentation.view_model.PokemonListState
import com.dev.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // Use collectAsState to observe the state in the ViewModel
                val pokemonListState by hiltViewModel<PokedexViewModel>().pokemonList.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (val state = pokemonListState) {
                        is PokemonListState.Loading -> {
                            // Show loading indicator or placeholder
                            Text("Loading...")
                        }
                        is PokemonListState.Error -> {
                            Text("Error: ${state.error}")
                        }
                        is PokemonListState.Success -> {
                            // Display the list of Pokemon
                            PokemonList(listOfPokemon = state.data.results)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {
        Greeting("Android")
    }
}