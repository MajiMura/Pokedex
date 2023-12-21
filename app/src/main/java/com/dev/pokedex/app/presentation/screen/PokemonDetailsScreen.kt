package com.dev.pokedex.app.presentation.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.dev.pokedex.app.domain.model.pokemon_details.Types
import com.dev.pokedex.app.presentation.components.pokemon_details.PokemonTypes
import com.dev.pokedex.app.presentation.util.decimetersToFeetInches
import com.dev.pokedex.app.presentation.util.digitCorrection
import com.dev.pokedex.app.presentation.util.kilogramsToPounds
import com.dev.pokedex.app.presentation.view_model.PokemonDetailsState
import com.dev.pokedex.app.presentation.view_model.PokemonDetailsViewModel
import com.dev.pokedex.ui.fonts.pocketMonkFamily
import com.dev.pokedex.ui.fonts.pokemonGameboyFamily
import com.dev.pokedex.ui.fonts.pokemonGameboyJapanHiraganaFamily
import java.util.Locale

@Composable
fun PokemonDetailsScreen(
    navigateUp: () -> Unit,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    val pokemonDetailsState by viewModel.pokemonDetails.collectAsState()

    Scaffold(
        topBar = {
            PokedexTopAppBar(
                pokemonDetailsState = pokemonDetailsState,
                onBackClicked = {
                    navigateUp()
                })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF978CD0), // Start color
                            Color(0xFF7367B0)
                        )
                    )
                )
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val state = pokemonDetailsState) {
                is PokemonDetailsState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {
                        // Row with Image and Text
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .heightIn(min = 150.dp, max = 150.dp)
                        ) {
                            // Column on the left with Text composables
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            Color(0xFFF9E4A2),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .border(
                                            2.dp,
                                            Color(0xFFEACC7D),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .fillMaxSize()
                                        .padding(20.dp)
                                ) {
                                    Column {
                                        PokemonName(name = state.pokemonDetails.name)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        PokemonId(id = state.pokemonDetails.id)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        PokemonHeight(height = state.pokemonDetails.height)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        PokemonWeight(weight = state.pokemonDetails.weight)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        PokemonTypes(types = state.pokemonDetails.types)
                                    }

                                }

                            }

                            // Image on the right
                            PokemonImage(
                                url = state.pokemonDetails.sprites?.frontDefault,

                                )
                        }
//                        Text(state.pokemonDetails?.locationAreaEncounters.toString()) // returns URL
                        Box(modifier = Modifier
                            .background(
                                Color(0xFFF9E4A2),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                2.dp,
                                Color(0xFFEACC7D),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .fillMaxSize()
                            .padding(20.dp)) {
                            Column (modifier = Modifier.fillMaxWidth()) {
                                Text("ABILITIES", style = TextStyle(fontFamily = pokemonGameboyFamily))
                                Text(state.pokemonDetails?.abilities.toString(), style = TextStyle(fontFamily = pokemonGameboyFamily))
                            }
                        }
                        Box(modifier = Modifier
                            .background(
                                Color(0xFFF9E4A2),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                2.dp,
                                Color(0xFFEACC7D),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .fillMaxSize()
                            .padding(20.dp)) {
                            Column (modifier = Modifier.fillMaxWidth()) {
                                Text("STATS", style = TextStyle(fontFamily = pokemonGameboyFamily))
                                Text(state.pokemonDetails?.stats.toString(), style = TextStyle(fontFamily = pokemonGameboyFamily))
                            }
                        }
                    }
                }


                is PokemonDetailsState.Loading -> {
                    Text("Loading...")
                }

                is PokemonDetailsState.Error -> {
                    Text("Error: ${state.error}")
                }
            }
        }
    }
}

@Composable
private fun PokemonName(name: String?) {
    Text(
        text = name?.let { it ->
            if (it.isBlank()) "-" else it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
        } ?: "-",
        style = TextStyle.Default.copy(
            fontFamily = pokemonGameboyFamily,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
private fun PokemonId(id: Int?) {
    Text(
        text = id?.let { it ->
            digitCorrection(it)
        } ?: "-",
        style = TextStyle.Default.copy(
            fontFamily = pokemonGameboyFamily,
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        ),
    )
}

@Composable
private fun PokemonHeight(height: Int?) {
    val heightString: String = height?.toString() ?: "-"
    if (heightString == "-") {
        return
    } else {
        val convertedHeight = decimetersToFeetInches(height!!)
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                "HT",
                style = TextStyle.Default.copy(
                    fontFamily = pokemonGameboyFamily,
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Left
                ),
            )
            Box(modifier = Modifier.width(130.dp)) {
                Text(
                    convertedHeight,
                    style = TextStyle.Default.copy(
                        fontFamily = pokemonGameboyFamily,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Left
                    ),
                )
            }
        }
    }
}

@Composable
private fun PokemonWeight(weight: Int?) {
    val weightString: String = weight?.toString() ?: "-"
    if (weightString == "-") {
        return
    } else {
        val convertedWeight = kilogramsToPounds(weight!!)
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                "WT",
                style = TextStyle.Default.copy(
                    fontFamily = pokemonGameboyFamily,
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                ),
            )
            Box(modifier = Modifier.width(130.dp)) {
                Text(
                    convertedWeight,
                    style = TextStyle.Default.copy(
                        fontFamily = pokemonGameboyFamily,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        }
    }
}

@Composable
private fun PokemonImage(url: String?) {
    Box(
        modifier = Modifier
            .background(Color(0xFFF9E4A2), shape = RoundedCornerShape(16.dp))
            .size(width = 160.dp, height = 160.dp)
            .border(2.dp, Color(0xFFEACC7D), shape = RoundedCornerShape(16.dp))
            .fillMaxSize(), // Fill the available space

        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .padding(vertical = 8.dp, horizontal = 4.dp)
                .border(2.dp, Color(0xFFEACC7D), shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Draw horizontal stripes
            DrawStripes()
            if (url != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = url)
                            .apply {
                                transformations(CircleCropTransformation())
                            }
                            .build()
                    ),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Pokemon Image",
                )
            }
        }
    }
}

@Composable
private fun DrawStripes() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val stripeHeight =
            size.height / 10 // Adjust the number of stripes by changing the denominator

        repeat(10) { index ->
            val color = if (index % 2 == 0) Color.White.copy(alpha = 0.9f) else Color.White
            drawRect(
                color = color,
                topLeft = Offset(0f, index * stripeHeight),
                size = Size(size.width, stripeHeight)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokedexTopAppBar(
    onBackClicked: () -> Unit,
    pokemonDetailsState: PokemonDetailsState,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            when (pokemonDetailsState) {
                is PokemonDetailsState.Success -> {
                    Text(
                        "GO BACK", style = TextStyle(
                            fontFamily = pocketMonkFamily,
                            fontSize = 26.sp
                        )
                    )
                }

                is PokemonDetailsState.Loading -> {
                    Text("Loading...")
                }

                is PokemonDetailsState.Error -> {
                    Text("Error: ${pokemonDetailsState.error}")
                }
            }
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
