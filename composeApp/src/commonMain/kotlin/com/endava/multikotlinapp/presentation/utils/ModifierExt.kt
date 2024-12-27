package com.endava.multikotlinapp.presentation.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.endava.multikotlinapp.presentation.theme.AppTheme

fun Modifier.contentModifier() =
    composed { fillMaxWidth().padding(horizontal = AppTheme.spacing.s6) }