package com.endava.multikotlinapp

import android.app.Application
import com.endava.multikotlinapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@KmmApplication)
        }
    }
}