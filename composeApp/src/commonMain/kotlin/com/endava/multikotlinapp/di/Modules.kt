package com.endava.multikotlinapp.di

import com.endava.multikotlinapp.data.HttpClientFactory
import com.endava.multikotlinapp.data.repositories.NewsRepository
import com.endava.multikotlinapp.data.repositories.NewsRepositoryImpl
import com.endava.multikotlinapp.presentation.screens.list.ListScreenViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModel { ListScreenViewModel(newsRepository = get()) }

    single<NewsRepository> {
        NewsRepositoryImpl(
            coroutineDispatcher = Dispatchers.IO, // TODO: inject this
            httpClient = get()
        )
    }
    single<HttpClient> { HttpClientFactory(get()).create() }
}