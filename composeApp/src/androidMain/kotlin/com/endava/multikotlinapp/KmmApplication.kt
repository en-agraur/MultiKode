package com.endava.multikotlinapp

import android.app.Application
import com.endava.multikotlinapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.lighthousegames.logging.KmLogging
import org.lighthousegames.logging.LogLevel

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KmLogging.setLogLevel(LogLevel.Debug) // TODO: handle this for debug and release
        initKoin {
            androidContext(this@KmmApplication)
        }
    }
}