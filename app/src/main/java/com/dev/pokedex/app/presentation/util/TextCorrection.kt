package com.dev.pokedex.app.presentation.util

fun digitCorrection(index: Int): String {
    return "#${String.format("%03d", index)}"
}

fun decimetersToFeetInches(decimeters: Int): String {
    val meters = decimeters / 10.0 // Convert decimeters to meters
    val totalInches = meters * 39.37
    val feet = totalInches.toInt() / 12
    val inches = totalInches.toInt() % 12
    return "$feet'${String.format("%02d", inches)}\""
}

fun kilogramsToPounds(input: Int): String {
    println("input: $input")
    val kg: Double = input / 10.0
    println("kg: $kg")
    val lbsConversionFactor = 2.20462
    // Conversion factor: 1 kg = 2.20462 lbs
    // Convert kg to lbs
    val lbs = kg * lbsConversionFactor
    // Format the result to one decimal place
    val formattedResult = String.format("%.1f", lbs)

    return "$formattedResult lbs."
}


