package com.dev.pokedex.app.presentation.components.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dev.pokedex.app.domain.model.Pokemon

@Composable
fun PokemonListItem(pokemon: Pokemon) {
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Surface {
            Row (modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()) {
//                Image(painter = rememberImagePainter(data = pokemon.url,
//                    builder = {
//                        scale(Scale.FILL);
//                        placeholder(coil.base.R.drawable.notification_action_background);
//                        transformations(CircleCropTransformation())
//                    }), contentDescription = pokemon.name,
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .weight(0.2f)
//                )
                Column (verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(text = pokemon.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold)
                    Text(text = pokemon.name,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp))
                    Text(text = pokemon.name, style = MaterialTheme.typography.bodyLarge,
                        maxLines = 2, overflow = TextOverflow.Ellipsis)

                }
            }
        }
    }
}