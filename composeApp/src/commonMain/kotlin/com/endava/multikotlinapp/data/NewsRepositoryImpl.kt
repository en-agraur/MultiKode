package com.endava.multikotlinapp.data

import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.domain.entities.dto.Source
import com.endava.multikotlinapp.domain.entities.responses.DataError
import com.endava.multikotlinapp.domain.entities.responses.EmptyResult
import com.endava.multikotlinapp.domain.entities.responses.Result

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getTopNews(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int,
        page: Int
    ): Result<List<ListItem>, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun getSources(category: String?, language: String?, country: String?): Result<List<Source>, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun getEverything(
        query: String?,
        searchIn: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
        pageSize: Int,
        page: Int
    ): Result<List<ListItem>, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleBookmark(item: ListItem): EmptyResult<DataError.Local> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookmarks(): Result<List<ListItem>, DataError.Local> {
        TODO("Not yet implemented")
    }
}