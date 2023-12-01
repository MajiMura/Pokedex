package com.dev.pokedex.di

import com.dev.pokedex.app.data.repository.PokedexRepositoryImpl
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokedexRepository(
        pokedexRepositoryImpl: PokedexRepositoryImpl
    ): PokedexRepository

}