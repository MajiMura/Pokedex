package com.dev.pokedex

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class PokedexTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loadList() {
        rule.setContent { PokedexApplication() }
    }
}