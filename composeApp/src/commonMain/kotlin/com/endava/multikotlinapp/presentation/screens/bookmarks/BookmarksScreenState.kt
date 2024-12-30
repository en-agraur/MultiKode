package com.endava.multikotlinapp.presentation.screens.bookmarks

import com.endava.multikotlinapp.domain.entities.dto.ListItem

data class BookmarksScreenState(
    val items: List<ListItem> = emptyList(),
    val isLoading: Boolean = true,
)
