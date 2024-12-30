package com.endava.multikotlinapp.data.network.responses

import com.endava.multikotlinapp.domain.entities.dto.ListItem

fun Article.toItemDTO() = ListItem(
    publishedAt = this.publishedAt,
    url = this.url, //this should be unique
    description = this.description,
    title = this.title,
    author = this.author,
    thumbnail = this.urlToImage,
    source = this.source?.name
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