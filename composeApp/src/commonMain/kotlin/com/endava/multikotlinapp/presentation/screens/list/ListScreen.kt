package com.endava.multikotlinapp.presentation.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel = koinViewModel(),
    onItemClicked: (String) -> Unit = {}
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ListScreenContent(modifier = modifier, state = state, onItemClicked = onItemClicked)
}


@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    state: ListScreenState,
    onItemClicked: (String) -> Unit
) {

}