package com.dev.pokedex.di

import com.dev.pokedex.app.data.remote.PokedexApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module defining dependencies for the entire application.
 *
 * [SingletonComponent] will live as long as the app does.
 * [Activity Component] will only live as long as the activity it is injected into.
 * [Retained Component] will not be destroyed when the screen is rotated, and the activity is recreated.
 * [Service Component] is used to inject dependencies into a service.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Create a Moshi instance using MoshiConverterFactory
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Use the Moshi instance in your Retrofit setup
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun providePokedexApi(): PokedexApi {
        return retrofit
            .create(PokedexApi::class.java)
    }
}