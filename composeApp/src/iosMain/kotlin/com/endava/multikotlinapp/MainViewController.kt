package com.endava.multikotlinapp

import androidx.compose.ui.window.ComposeUIViewController
import com.endava.multikotlinapp.di.initKoin
import com.endava.multikotlinapp.presentation.KmmApp
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController(
        configure = { initKoin() },
        content = { KmmApp() }
    )
}