package com.endava.multikotlinapp.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.multikotlinapp.data.NewsRepository
import com.endava.multikotlinapp.domain.entities.responses.onError
import com.endava.multikotlinapp.domain.entities.responses.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListScreenViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state

    private fun fetchItems() {
        viewModelScope.launch {
            fetchSources()
            fetchEverything()
        }
    }

    private suspend fun fetchEverything() {
        newsRepository.getEverything(page = 1)
            .onSuccess { items -> _state.update { it.copy(items = items, isLoading = false) } }
            .onError { _state.update { it.copy(errorMessage = "error fetching news") } }
    }

    private suspend fun fetchSources() {
        newsRepository.getSources()
            .onSuccess { items -> _state.update { it.copy(sources = items, isLoading = false) } }
            .onError { _state.update { it.copy(errorMessage = "error fetching sources") } }
    }
}