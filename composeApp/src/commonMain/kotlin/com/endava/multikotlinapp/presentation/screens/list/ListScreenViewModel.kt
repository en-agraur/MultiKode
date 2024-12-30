package com.endava.multikotlinapp.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.multikotlinapp.data.repositories.NewsRepository
import com.endava.multikotlinapp.data.repositories.TECHNOLOGY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListScreenViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state

    private val _selectedSources = MutableStateFlow<List<String>>(emptyList())
    val selectedSources = _selectedSources.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            val value = selectedSources.value
            val selectedSourcesString = value.joinToString(separator = ",")
            val news = newsRepository.getTopNews(
                page = 1,
                sources = if (value.isNotEmpty()) selectedSourcesString else null,
                category = if (value.isEmpty()) TECHNOLOGY else null
            )
            val sources = newsRepository.getSources()
            _state.update { it.copy(items = news, sources = sources, isLoading = false, isRefreshing = false) }
        }
    }

    fun onRefresh() {
        _state.update { it.copy(isRefreshing = true) }
        fetchItems()
    }

    fun onSourceClicked(source: String) {
        val existingSources = selectedSources.value.toMutableList()
        if (existingSources.contains(source)) {
            existingSources.remove(source)
        } else {
            existingSources.add(source)
        }
        _selectedSources.update { existingSources }
        _state.update { it.copy(isRefreshing = true) }
        fetchItems()
    }
}