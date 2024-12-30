package com.endava.multikotlinapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.endava.multikotlinapp.di.initKoin
import com.endava.multikotlinapp.presentation.KmmApp
import javafx.application.Platform

fun main() {
    Platform.startup {} // Ensure JavaFX is initialized (for webview)

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