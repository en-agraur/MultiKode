package com.endava.multikotlinapp.data.repositories

import com.endava.multikotlinapp.data.responses.Article
import com.endava.multikotlinapp.data.responses.Source
import com.endava.multikotlinapp.domain.entities.dto.ListItem
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun Article.toItemDTO() = ListItem(
    publishedAt = this.publishedAt,
    url = this.url,
    source = this.source?.toItemDTO(),
    description = this.description,
    title = this.title,
    author = this.author,
    thumbnail = this.urlToImage,
    id = Uuid.random().toString()
)

fun Source.toItemDTO() = com.endava.multikotlinapp.domain.entities.dto.Source(
    url = this.url,
    description = this.description,
    name = this.name,
    id = this.id,
    category = this.category,
    language = this.language,
    country = this.language
)