package com.endava.multikotlinapp.presentation.screens.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.presentation.theme.AppTheme
import com.endava.multikotlinapp.presentation.utils.contentModifier
import multikotlinapp.composeapp.generated.resources.Res
import multikotlinapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardItemComponent(
    modifier: Modifier = Modifier,
    item: ListItem,
    onItemClicked: () -> Unit = {},
    context: PlatformContext = LocalPlatformContext.current
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
        Column(horizontalAlignment = Alignment.Start) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth().aspectRatio(16 / 9f),
                model = ImageRequest
                    .Builder(context)
                    .data(item.thumbnail)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "image",
                clipToBounds = true,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.compose_multiplatform),
                error = painterResource(Res.drawable.compose_multiplatform),
                filterQuality = FilterQuality.High,
            )

            Column(
                modifier = Modifier
                    .contentModifier()
                    .padding(vertical = AppTheme.spacing.s6)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.source?.name.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                    )

                    Text(
                        text = item.publishedAt.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
                Text(
                    text = item.title.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = item.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}