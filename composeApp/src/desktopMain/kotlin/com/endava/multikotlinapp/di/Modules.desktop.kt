package com.endava.multikotlinapp.di

import com.endava.multikotlinapp.data.local.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { DatabaseFactory() }

}