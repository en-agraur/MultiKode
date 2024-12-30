package com.endava.multikotlinapp.data.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    @SerialName("articles") val articles: List<Article> = emptyList(),
    @SerialName("status") val status: String = "",
    @SerialName("totalResults") val totalResults: Int = 0
)