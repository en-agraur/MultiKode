package com.endava.multikotlinapp.presentation.screens.list

import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.domain.entities.dto.Source

data class ListScreenState(
    val items: List<ListItem> = emptyList(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val readLaterItems: List<String> = emptyList(), //list of items that we want to read later
    val sources: List<Source> = emptyList(),
    val errorMessage: String? = null
)
