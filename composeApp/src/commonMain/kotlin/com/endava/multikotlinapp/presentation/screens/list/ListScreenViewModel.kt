package com.endava.multikotlinapp.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.multikotlinapp.di.NewsRepository
import com.endava.multikotlinapp.di.TECHNOLOGY
import com.endava.multikotlinapp.domain.entities.dto.ListItem
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
            val news = fetchTopNews()
            val readLaterItems = newsRepository.getBookmarks().mapNotNull { it.url }
            val sources = newsRepository.getSources()
            _state.update {
                it.copy(
                    items = news,
                    sources = sources,
                    readLaterItems = readLaterItems,
                    isLoading = false
                )
            }
        }
    }

    private suspend fun fetchTopNews(): List<ListItem> {
        val value = selectedSources.value
        val selectedSourcesString = value.joinToString(separator = ",")
        val news = newsRepository.getTopNews(
            page = 1,
            sources = if (value.isNotEmpty()) selectedSourcesString else null,
            category = if (value.isEmpty()) TECHNOLOGY else null
        )
        return news
    }

    fun onRefresh() {
        _state.update { it.copy(isRefreshing = true) }
        viewModelScope.launch {
            val news = fetchTopNews()
            _state.update {
                it.copy(
                    isRefreshing = false,
                    items = news,
                )
            }
        }

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

    fun onReadLaterPressed(isReadLater: Boolean, item: ListItem) {
        viewModelScope.launch {
            newsRepository.toggleBookmark(item, isReadLater)
            val readLaterItems = newsRepository.getBookmarks().mapNotNull { it.url }
            _state.update {
                it.copy(
                    readLaterItems = readLaterItems
                )
            }
        }
    }
}