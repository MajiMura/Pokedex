package com.dev.pokedex.di

import android.content.Context
import com.dev.pokedex.PokedexApplication
import com.dev.pokedex.app.data.remote.PokedexApi
import com.dev.pokedex.app.data.repository.PokedexRepositoryImpl
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
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

    @Provides
    @Singleton
    fun providePokedexApi(): PokedexApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokedexApi::class.java)
    }
}