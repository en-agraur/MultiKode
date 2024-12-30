package com.endava.multikotlinapp.presentation.screens.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.multikotlinapp.di.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarksScreenState())
    val state: StateFlow<BookmarksScreenState> = _state

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch {
            val bookmarks = repository.getBookmarks()
            _state.update { it.copy(items = bookmarks, isLoading = false) }
        }
    }

}