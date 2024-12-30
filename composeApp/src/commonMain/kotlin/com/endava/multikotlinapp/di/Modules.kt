package com.endava.multikotlinapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.endava.multikotlinapp.data.local.DatabaseFactory
import com.endava.multikotlinapp.data.local.KmmDatabase
import com.endava.multikotlinapp.data.network.HttpClientFactory
import com.endava.multikotlinapp.data.repositories.NewsRepositoryImpl
import com.endava.multikotlinapp.presentation.screens.bookmarks.BookmarksViewModel
import com.endava.multikotlinapp.presentation.screens.list.ListScreenViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single<HttpClient> { HttpClientFactory(get()).create() }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { get<KmmDatabase>().getReadLaterDao() }
    viewModelOf(::ListScreenViewModel)
    viewModelOf(::BookmarksViewModel)

    single<NewsRepository> {
        NewsRepositoryImpl(
            coroutineDispatcher = Dispatchers.IO, // TODO: inject this
            httpClient = get(),
            readLaterDao = get()
        )
    }
}