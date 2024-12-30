package com.endava.multikotlinapp.data.repositories

import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.domain.entities.dto.Source

private const val PAGE_SIZE_COUNT = 100

internal const val BASE_URL = "https://newsapi.org/v2"
internal const val TOP_HEADLINES_URL = "$BASE_URL/top-headlines"
internal const val SOURCES_URL = "$TOP_HEADLINES_URL/sources"
internal const val API_KEY_VALUE = "06400ade1ea145bcb1568d279a29f9b8"
internal const val API_KEY = "X-Api-Key"
internal const val QUERY = "query"
internal const val SOURCES = "sources"
internal const val CATEGORY = "category"
internal const val COUNTRY = "country"
internal const val PAGE = "page"
internal const val PAGE_SIZE = "pageSize"
internal const val LANGUAGE = "language"
private const val DEFAULT_LANGUAGE = "en"
const val TECHNOLOGY = "technology"

interface NewsRepository {

    suspend fun getTopNews(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        query: String? = null,
        pageSize: Int = PAGE_SIZE_COUNT,
        page: Int = 1
    ): List<ListItem>

    suspend fun getSources(
        category: String? = TECHNOLOGY,
        language: String? = DEFAULT_LANGUAGE,
        country: String? = null
    ): List<Source>


    suspend fun toggleBookmark(item: ListItem)

    suspend fun getBookmarks(): List<ListItem>
}