package com.endava.multikotlinapp.data.repositories

import com.endava.multikotlinapp.data.responses.ArticlesResponse
import com.endava.multikotlinapp.data.responses.SourcesResponse
import com.endava.multikotlinapp.domain.entities.dto.ListItem
import com.endava.multikotlinapp.domain.entities.dto.Source
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.lighthousegames.logging.logging


class NewsRepositoryImpl(
    private val httpClient: HttpClient,
    private val coroutineDispatcher: CoroutineDispatcher
) : NewsRepository {

    private val log = logging(NewsRepositoryImpl::class.simpleName)

    override suspend fun getTopNews(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int,
        page: Int
    ): List<ListItem> = withContext(coroutineDispatcher) {
        val networkRequest: ArticlesResponse = httpClient.get(urlString = TOP_HEADLINES_URL) {
            query?.let { parameter(QUERY, query) }
            //you can't match sources with the country or category params
            sources?.let { parameter(SOURCES, sources) }
            category?.let { parameter(CATEGORY, category) }
            country?.let { parameter(COUNTRY, country) }

            parameter(PAGE, page)
            parameter(PAGE_SIZE, pageSize)
        }.body<ArticlesResponse>()

        log.debug { networkRequest }

        //todo create a wrapper over this to handle better the responses
        if (networkRequest.status == "ok") {
            return@withContext networkRequest.articles.map { it.toItemDTO() }
        }

        return@withContext emptyList()

    }

    override suspend fun getSources(category: String?, language: String?, country: String?): List<Source> = withContext(coroutineDispatcher) {
        val networkRequest: SourcesResponse = httpClient.get(urlString = SOURCES_URL) {
            language?.let { parameter(LANGUAGE, language) }
            category?.let { parameter(CATEGORY, category) }
            country?.let { parameter(COUNTRY, country) }
        }.body<SourcesResponse>()

        log.debug { networkRequest }

        //todo create a wrapper over this to handle better the responses
        if (networkRequest.status == "ok") {
            return@withContext networkRequest.sources.map { it.toItemDTO() }
        }

        return@withContext emptyList()
    }

    override suspend fun toggleBookmark(item: ListItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getBookmarks(): List<ListItem> {
        TODO("Not yet implemented")
    }
}