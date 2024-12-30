package com.endava.multikotlinapp.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

//android modules
actual val platformModule: Module = module {
    single<HttpClientEngine> { OkHttp.create() }
}