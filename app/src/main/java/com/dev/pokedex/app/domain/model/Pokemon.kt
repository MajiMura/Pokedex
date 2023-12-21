package com.dev.pokedex.app.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class Pokemon(
    val name : String,
    @field:Json(name = "url")
    val url: String
)