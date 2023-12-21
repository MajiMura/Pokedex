package com.dev.pokedex.app.presentation.components.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.pokedex.app.domain.model.pokemon_details.Types
import com.dev.pokedex.ui.fonts.pokemonGameboyFamily
import com.dev.pokedex.ui.theme.bugColor
import com.dev.pokedex.ui.theme.darkColor
import com.dev.pokedex.ui.theme.dragonColor
import com.dev.pokedex.ui.theme.electricColor
import com.dev.pokedex.ui.theme.fairyColor
import com.dev.pokedex.ui.theme.fightingColor
import com.dev.pokedex.ui.theme.fireColor
import com.dev.pokedex.ui.theme.flyingColor
import com.dev.pokedex.ui.theme.ghostColor
import com.dev.pokedex.ui.theme.grassColor
import com.dev.pokedex.ui.theme.groundColor
import com.dev.pokedex.ui.theme.iceColor
import com.dev.pokedex.ui.theme.normalColor
import com.dev.pokedex.ui.theme.poisonColor
import com.dev.pokedex.ui.theme.psychicColor
import com.dev.pokedex.ui.theme.rockColor
import com.dev.pokedex.ui.theme.steelColor
import com.dev.pokedex.ui.theme.waterColor

@Composable
fun PokemonTypes(types: List<Types>) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()) {
        types.forEach { type ->
            type.type?.name?.let { TypeSelect(it.uppercase()) }
        }
    }
}

@Composable
private fun TypeSelect(type: String) {
    when (type) {
        "BUG" -> { TypeBox(name = "BUG", color = bugColor) }
        "DARK" -> { TypeBox(name = "DARK", color = darkColor) }
        "DRAGON" -> { TypeBox(name = "DRAGON", color = dragonColor) }
        "ELECTRIC" -> { TypeBox(name = "ELECTRIC", color = electricColor) }
        "FAIRY" -> { TypeBox(name = "FAIRY", color = fairyColor) }
        "FIGHTING" -> { TypeBox(name = "FIGHTING", color = fightingColor) }
        "FIRE" -> { TypeBox(name = "FIRE", color = fireColor) }
        "FLYING" -> { TypeBox(name = "FLYING", color = flyingColor) }
        "GHOST" -> { TypeBox(name = "GHOST", color = ghostColor) }
        "GRASS" -> { TypeBox(name = "GRASS", color = grassColor) }
        "GROUND" -> { TypeBox(name = "GROUND", color = groundColor) }
        "ICE" -> { TypeBox(name = "ICE" ,color = iceColor) }
        "NORMAL" -> { TypeBox(name = "NORMAL", color = normalColor) }
        "WATER" -> { TypeBox(name = "WATER", color = waterColor) }
        "POISON" -> { TypeBox(name = "POISON", color = poisonColor) }
        "PSYCHIC" -> { TypeBox(name = "PSYCHIC", color = psychicColor) }
        "STEEL" -> { TypeBox(name = "STEEL", color = steelColor) }
        "ROCK" -> { TypeBox(name = "ROCK", color = rockColor) }
        else -> {
            TypeBox(name = "???", color = Color.LightGray)
        }
    }
}

@Composable
private fun TypeBox(name: String, color: Color) {
    val offset = Offset(1.0f, 2.0f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(4.dp))
            .height(28.dp)
            .width(100.dp)
            .padding(4.dp)
    ) {
        // Text with shadow
        Text(
            text = name,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            style = TextStyle(
                fontFamily = pokemonGameboyFamily,
                color = Color.White,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 3f
                )
            )
        )
    }
}
