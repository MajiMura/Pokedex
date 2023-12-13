package com.dev.pokedex
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.test.filters.LargeTest
import com.dev.pokedex.app.presentation.screen.LoginScreen
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

const val TEST_USERNAME_STRING = "UsernameHere"
const val TEST_PASSWORD_STRING = "Password123!"
@LargeTest
class PokedexLoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        composeTestRule.setContent {
            LoginScreen(navController = mock(NavController::class.java))
        }

        // Use the composeTestRule to interact with the UI components
        composeTestRule.onNodeWithText("Pokédex").assertIsDisplayed()

        // Type text into the UsernameTextField
        composeTestRule.onNodeWithText("Username").performTextInput(TEST_USERNAME_STRING)

        // Type text into the PasswordTextField
        composeTestRule.onNodeWithText("Password").performTextInput(TEST_PASSWORD_STRING)

        // Click the "Login" button
        composeTestRule.onNodeWithText("Login").performClick()

        // Add additional assertions based on your UI flow
    }
}