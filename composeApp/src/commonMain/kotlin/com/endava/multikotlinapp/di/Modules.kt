package com.endava.multikotlinapp.di

import com.endava.multikotlinapp.data.NewsRepository
import com.endava.multikotlinapp.data.NewsRepositoryImpl
import com.endava.multikotlinapp.presentation.screens.list.ListScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::ListScreenViewModel)
    singleOf<NewsRepository>(::NewsRepositoryImpl)
}