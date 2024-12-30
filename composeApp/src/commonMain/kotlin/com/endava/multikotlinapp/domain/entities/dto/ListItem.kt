package com.endava.multikotlinapp.domain.entities.dto

data class ListItem(
    val id: String,
    val author: String?,
    val title: String?,
    val description: String?,
    val thumbnail: String?,
    val url: String?,
    val publishedAt: String?,
    val source: Source?
)

