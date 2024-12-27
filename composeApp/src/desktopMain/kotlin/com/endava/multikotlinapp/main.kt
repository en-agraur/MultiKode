package com.endava.multikotlinapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.endava.multikotlinapp.di.initKoin
import com.endava.multikotlinapp.presentation.KmmApp

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MultiKotlinApp",
        ) {
            KmmApp()
        }
    }
}