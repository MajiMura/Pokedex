package com.dev.pokedex.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun PokemonOverviewScreen (pokemonId: Int, navigateUp: () -> Unit) {
    Scaffold (
        topBar = { PokedexTopAppBar(onBackClicked = {
            navigateUp()
        }) }, ) { innerPadding ->
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF978CD0), // Start color
                        Color(0xFF7367B0)
                    )
                )).padding(innerPadding).fillMaxSize()
        ) {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokedexTopAppBar(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text("Pokemon Name")
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack Icon")
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
