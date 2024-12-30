package com.endava.multikotlinapp.presentation.screens.bookmarks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun BookmarksScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel,
    onNavToDetails: (String, String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookmarksScreenContent(
        modifier = modifier.fillMaxSize(),
        state = state,
        onNavToDetails = onNavToDetails
    )

}

@Composable
fun BookmarksScreenContent(
    modifier: Modifier = Modifier,
    state: BookmarksScreenState,
    scrollState: LazyListState = rememberLazyListState(),
    onNavToDetails: (String, String) -> Unit
) {
    val context = LocalPlatformContext.current

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = state.items,
            key = { it.url.toString() }
        ) {
            BookMarkListItem(
                modifier = Modifier.fillMaxWidth(),
                item = it,
                onItemClicked = { onNavToDetails(it.url.toString(), it.title.toString()) },
                context = context
            )
        }
    }

}

@Composable
fun BookMarkListItem(
    modifier: Modifier, item: ListItem,
    onItemClicked: () -> Unit,
    context: PlatformContext,
) {
    OutlinedCard(
        modifier = modifier
            .clickable { onItemClicked() }
            .padding(horizontal = AppTheme.spacing.s6, vertical = AppTheme.spacing.s2)
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = AppTheme.spacing.none),
        border = BorderStroke(width = AppTheme.spacing.xs1, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(AppTheme.spacing.s14)
                    .aspectRatio(1f),
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
                modifier = Modifier.contentModifier()
            ) {
                Text(
                    text = item.title.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}
