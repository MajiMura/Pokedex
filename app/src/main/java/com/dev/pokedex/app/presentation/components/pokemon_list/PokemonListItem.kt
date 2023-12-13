package com.dev.pokedex.app.presentation.components.pokemon_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.pokedex.app.domain.model.Pokemon

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonListItem(pokemon: Pokemon, index: Int, selectedPokemon: (Int) -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val interactionState = rememberUpdatedState(isPressed)

    LaunchedEffect(interactionState.value) {
        if (!interactionState.value) {
            isPressed = false
        }
    }

    ElevatedCard(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(60.dp)
        .clip(RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { selectedPokemon(index) },
    ) {
        Surface (color = if (interactionState.value) Color.Gray else Color(0xFFDE0024)) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()) {
                Text(text = pokemon.name.uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 16.dp)
                    )
                Text(text = digitCorrection(index),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 16.dp))
            }
        }
    }
}

fun digitCorrection(index: Int): String {
    return "#${String.format("%03d", index)}"
}
