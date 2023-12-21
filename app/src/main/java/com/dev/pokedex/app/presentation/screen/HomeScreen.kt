package com.dev.pokedex.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.pokedex.app.presentation.components.pokemon_list.PokemonList
import com.dev.pokedex.app.presentation.view_model.HomeViewModel
import com.dev.pokedex.app.presentation.view_model.PokemonListState

@Composable
fun HomeScreen (selectedPokemon: (String) -> Unit, viewModel: HomeViewModel = hiltViewModel()) {
    val pokemonListState by viewModel.pokemonList.collectAsState()
    val scrollState = rememberLazyListState()
    when (val state = pokemonListState) {
        is PokemonListState.Loading -> {
            // Show loading indicator or placeholder
            Text("Loading...")
        }
        is PokemonListState.Error -> {
            Text("Error: ${state.error}")
        }
        is PokemonListState.Success -> {
            Scaffold (
                topBar = { PokedexTopAppBar(onSearchTextChanged = { value -> viewModel.searchPokemon(value) },
                    onMicClicked = {}, onBurgerClicked = {}) },) { innerPadding ->
                Box(
                    modifier = Modifier.background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF978CD0), // Start color
                                Color(0xFF7367B0)
                            )
                        )).padding(innerPadding).fillMaxSize()
                ) {
                    PokemonList(listOfPokemon = state.allPokemon,
                        scrollState = scrollState,
                        modifier = Modifier
                            .fillMaxSize(),
                        loadMore = { viewModel.loadMore() },
                        selectedPokemon = selectedPokemon
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokedexTopAppBar(
    onSearchTextChanged: (String) -> Unit,
    onMicClicked: () -> Unit,
    onBurgerClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            // Use a custom search bar
            PokedexSearchBar(text = searchText,
                onSearchTextChanged = {
                    searchText = it
                    onSearchTextChanged(it)
                },
                onMicClicked = {
                    onMicClicked()
                })
        },
        navigationIcon = {
            // Burger Icon
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFDE0024),
            scrolledContainerColor = Color(0xFFDE0024),
            navigationIconContentColor = Color.Yellow,
            titleContentColor = Color.Yellow,
            actionIconContentColor = Color.Yellow
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PokedexSearchBar (
    text: String,
    onSearchTextChanged: (String) -> Unit,
    onMicClicked: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    // used to ensure a TextField is focused when showing keyboard
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { onSearchTextChanged(it) },
        label = { Text("Search", color = Color.White) },
        textStyle = MaterialTheme.typography.titleSmall,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchTextChanged(text)
                focusRequester.freeFocus()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            // Mic icon
            IconButton(onClick = { onMicClicked() }) {
                Icon(Icons.Filled.Mic,
                    contentDescription = "Menu Icon",
                    tint = Color.White)
            }
        },
        modifier = Modifier.focusRequester(focusRequester).fillMaxWidth(),
    )
}