package com.endava.multikotlinapp.data

import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.domain.entities.dto.Source
import com.endava.multikotlinapp.domain.entities.responses.DataError
import com.endava.multikotlinapp.domain.entities.responses.EmptyResult
import com.endava.multikotlinapp.domain.entities.responses.Result

private const val PAGE_SIZE = 20

interface NewsRepository {
    suspend fun getTopNews(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        query: String? = null,
        pageSize: Int = PAGE_SIZE,
        page: Int
    ): Result<List<ListItem>, DataError.Remote>

    suspend fun getSources(
        category: String? = null,
        language: String? = null,
        country: String? = null
    ): Result<List<Source>, DataError.Remote>

    suspend fun getEverything(
        query: String? = null,
        searchIn: String? = null,
        sources: String? = null,
        domains: String? = null,
        excludeDomains: String? = null,
        from: String? = null,
        to: String? = null,
        language: String? = null,
        sortBy: String? = null,
        pageSize: Int = PAGE_SIZE,
        page: Int
    ): Result<List<ListItem>, DataError.Remote>

    suspend fun toggleBookmark(item: ListItem): EmptyResult<DataError.Local>

    suspend fun getBookmarks(): Result<List<ListItem>, DataError.Local>
}