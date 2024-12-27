package com.endava.multikotlinapp.presentation.screens.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.presentation.theme.AppTheme

@Composable
fun CardItemComponent(
    modifier: Modifier = Modifier,
    item: ListItem,
    onItemClicked: () -> Unit = {}
) {
    OutlinedCard(
        modifier = modifier
            .padding(horizontal = AppTheme.spacing.s6, vertical = AppTheme.spacing.s2)
            .clickable { onItemClicked() }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = AppTheme.spacing.none),
        border = BorderStroke(width = AppTheme.spacing.xs1, color = MaterialTheme.colorScheme.outline)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s2)
        ) {

            AsyncImage(
                model = item.thumbnail,
                contentDescription = "image",
            )
        }
    }
}