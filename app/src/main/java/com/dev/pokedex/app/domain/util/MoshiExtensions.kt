package com.dev.pokedex.app.domain.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
Usage for extensions.
Initialize Moshi in your Converter class
 */
private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * [Moshi] extension to transform a [List] to Json
 * */
inline fun <reified T> Moshi.listToJson(data: List<T>): String =
    adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        .toJson(data)

/**
 * [Moshi] extension to transform a json object to a [List]
 * */
inline fun <reified T> Moshi.jsonToList(json: String): List<T>? =
    adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        .fromJson(json)

/**
 * [Moshi] extension to transform a map to Json
 * */
inline fun <reified T, reified K> Moshi.mapToJson(data: Map<T, K>): String =
    adapter<Map<T, K>>(
        Types.newParameterizedType(
            MutableMap::class.java,
            T::class.java, K::class.java
        )
    ).toJson(data)

/**
 * [Moshi] extension to transform a json to Map
 * */
inline fun <reified T, reified K> Moshi.jsonToMap(json: String): Map<T, K>? =
    adapter<Map<T, K>>(
        Types.newParameterizedType(
            MutableMap::class.java,
            T::class.java,
            K::class.java
        )
    ).fromJson(json)


/*
* [Moshi] extension to transform an object to json
* */
inline fun <reified T> Moshi.objectToJson(data: T): String =
    adapter(T::class.java).toJson(data)

/*
* [Moshi] extension to transform json to an object
* */
inline fun <reified T> Moshi.jsonToObject(json: String): T? =
    adapter(T::class.java).fromJson(json)

/**
 * [Moshi] extension to transform multiple lists
 * */
inline fun <reified T> Moshi.multipleListsToJson(data: List<List<T>>): String =
    adapter<List<List<T>>>(
        Types.newParameterizedType(
            List::class.java,
            T::class.java
        )
    ).toJson(data)

inline fun <reified T> Moshi.jsonToMultipleLists(json: String): List<List<T>>? =
    adapter<List<List<T>>>(
        Types.newParameterizedType(
            List::class.java,
            T::class.java
        )
    ).fromJson(json)
// End transform multiple lists