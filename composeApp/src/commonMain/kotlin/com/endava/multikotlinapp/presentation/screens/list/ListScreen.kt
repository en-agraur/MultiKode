package com.endava.multikotlinapp.presentation.screens.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.endava.multikotlinapp.presentation.screens.list.components.CardItemComponent
import com.endava.multikotlinapp.presentation.theme.AppTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel,
    onNavToDetails: (String, String) -> Unit = { _, _ -> }
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedSources by viewModel.selectedSources.collectAsStateWithLifecycle()

    ListScreenContent(
        modifier = modifier,
        state = state,
        onRefresh = viewModel::onRefresh,
        onItemClicked = onNavToDetails,
        onSourceClicked = viewModel::onSourceClicked,
        selectedSources = selectedSources
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    state: ListScreenState,
    onItemClicked: (String, String) -> Unit,
    scrollState: LazyListState = rememberLazyListState(),
    onRefresh: () -> Unit = {},
    onSourceClicked: (String) -> Unit = {},
    selectedSources: List<String>?
) {

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    val context = LocalPlatformContext.current

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        state = rememberPullToRefreshState(),
        onRefresh = onRefresh,
        content = {
            LazyColumn(
                modifier = modifier,
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s3),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val sources = state.sources
                stickyHeader(key = "sources") {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s3),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                    ) {
                        items(
                            items = sources,
                            key = { it.id.toString() }
                        ) {
                            val isSelected = selectedSources?.contains(it.id) == true
                            FilterChip(
                                onClick = { onSourceClicked(it.id.toString()) },
                                label = { Text(it.name.toString()) },
                                selected = isSelected,
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.then(
                                    when {
                                        sources.indexOf(it) == 0 -> Modifier.padding(start = AppTheme.spacing.s6)
                                        sources.indexOf(it) == sources.size - 1 -> Modifier.padding(end = AppTheme.spacing.s6)
                                        else -> Modifier
                                    }
                                ),
                                leadingIcon = {
                                    if (isSelected) {
                                        Icon(imageVector = Icons.Default.Check, contentDescription = "selected")
                                    }
                                }
                            )
                        }
                    }
                }
                items(
                    items = state.items,
                    key = { it.id }
                ) { item ->
                    CardItemComponent(
                        modifier = Modifier.fillMaxWidth(),
                        onItemClicked = { onItemClicked(item.url.toString(), item.title.toString()) },
                        item = item,
                        context = context
                    )
                }
            }
        }
    )
}