package com.endava.multikotlinapp.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

//ios modules
actual val platformModule: Module = module {
    single<HttpClientEngine> { Darwin.create() }
}