import AppDestinations.POKEMON_OVERVIEW_ID_KEY
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.pokedex.app.presentation.screen.HomeScreen
import com.dev.pokedex.app.presentation.screen.LoginScreen
import com.dev.pokedex.app.presentation.screen.PokemonOverviewScreen

/**
 * Destinations used in the App
 */

private object AppDestinations {
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"
    const val POKEMON_OVERVIEW_ROUTE = "pokemon_overview"
    const val POKEMON_OVERVIEW_ID_KEY = "pokemonId"
}

private class AppActions(
    private val navController: NavHostController
) {
    val selectedPokemon: (Int) -> Unit = { pokemonId: Int ->
        navController.navigate("${AppDestinations.POKEMON_OVERVIEW_ROUTE}/$pokemonId")
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToHome: () -> Unit = {
        navController.navigate(AppDestinations.HOME_ROUTE) {
            popUpTo(AppDestinations.LOGIN_ROUTE) {
                inclusive = true
            }
        }
    }
}

@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.LOGIN_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestinations.LOGIN_ROUTE,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            LoginScreen(navigateToHome = actions.navigateToHome)
        }
        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen(selectedPokemon = actions.selectedPokemon)
        }
        composable(
            route = "${AppDestinations.POKEMON_OVERVIEW_ROUTE}/{$POKEMON_OVERVIEW_ID_KEY}",
            arguments = listOf(
                navArgument(POKEMON_OVERVIEW_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            PokemonOverviewScreen(
                pokemonId = arguments.getInt(POKEMON_OVERVIEW_ID_KEY),
                navigateUp = actions.navigateUp
            )
        }
    }
}