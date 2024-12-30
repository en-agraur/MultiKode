package com.endava.multikotlinapp.di

import com.endava.multikotlinapp.data.HttpClientFactory
import com.endava.multikotlinapp.data.repositories.NewsRepository
import com.endava.multikotlinapp.data.repositories.NewsRepositoryImpl
import com.endava.multikotlinapp.presentation.screens.list.ListScreenViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::ListScreenViewModel)

    factory<CoroutineDispatcher>(named("io")) { Dispatchers.IO }

    single<NewsRepository> {
        NewsRepositoryImpl(
            coroutineDispatcher = get(named("io")),
            httpClient = get()
        )
    }
    single<HttpClient> { HttpClientFactory(get()).create() }
}