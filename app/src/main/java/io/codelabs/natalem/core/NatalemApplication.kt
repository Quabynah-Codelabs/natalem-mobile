package io.codelabs.natalem.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NatalemApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin here
        startKoin {
            androidContext(this@NatalemApplication)

            modules(roomModule)
        }
    }
}