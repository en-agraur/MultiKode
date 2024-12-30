package com.endava.multikotlinapp.presentation.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformWebView(modifier: Modifier, url: String)

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    url: String
) {
    PlatformWebView(modifier = modifier.fillMaxSize(), url = url)
}