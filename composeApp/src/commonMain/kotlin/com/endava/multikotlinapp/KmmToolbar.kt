package com.endava.multikotlinapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import multikotlinapp.composeapp.generated.resources.Res
import multikotlinapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun KmmToolbar(
    title: String,
    modifier: Modifier = Modifier,
    hasBackAction: Boolean,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 2.dp,
        navigationIcon = {
            val iconModifier = Modifier.size(24.dp)
            if (hasBackAction) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, modifier = iconModifier.clickable(true, onClick = onBack), contentDescription = "back")
            } else {
                Icon(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    modifier = iconModifier
                )
            }
        },
        windowInsets = WindowInsets.statusBars,
    )
}