package com.dev.pokedex.app.presentation.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dev.pokedex.app.domain.repository.PokedexRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokedexService: Service() {

    @Inject
    lateinit var repository: PokedexRepository

    override fun onCreate() {
        super.onCreate()
        // repository...
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}