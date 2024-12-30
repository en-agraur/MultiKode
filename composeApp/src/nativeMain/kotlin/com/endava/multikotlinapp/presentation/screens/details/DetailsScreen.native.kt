package com.endava.multikotlinapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.javaScriptEnabled

@Composable
actual fun PlatformWebView(modifier: Modifier, url: String) {

    val link = remember { url }
    val webview = remember { WKWebView() }

    UIKitView(
        modifier = modifier,
        factory = {
            webview.apply {
                val nsUrl = NSURL(string = link)
                val request = NSURLRequest(nsUrl)
                loadRequest(request)
                allowsBackForwardNavigationGestures = true
                configuration.preferences.javaScriptEnabled = true
            }
        },
        onRelease = {
            webview.stopLoading()
            webview.navigationDelegate = null
        },
    )
}