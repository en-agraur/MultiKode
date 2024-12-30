package com.endava.multikotlinapp.data.repositories

import com.endava.multikotlinapp.data.local.daos.ReadLaterDao
import com.endava.multikotlinapp.data.local.entities.ReadLaterItem
import com.endava.multikotlinapp.data.network.responses.Article
import com.endava.multikotlinapp.data.network.responses.ArticlesResponse
import com.endava.multikotlinapp.data.network.responses.SourcesResponse
import com.endava.multikotlinapp.data.network.responses.toItemDTO
import com.endava.multikotlinapp.di.CATEGORY
import com.endava.multikotlinapp.di.COUNTRY
import com.endava.multikotlinapp.di.LANGUAGE
import com.endava.multikotlinapp.di.NewsRepository
import com.endava.multikotlinapp.di.PAGE
import com.endava.multikotlinapp.di.PAGE_SIZE
import com.endava.multikotlinapp.di.QUERY
import com.endava.multikotlinapp.di.SOURCES
import com.endava.multikotlinapp.di.SOURCES_URL
import com.endava.multikotlinapp.di.TOP_HEADLINES_URL
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
    private val coroutineDispatcher: CoroutineDispatcher,
    private val readLaterDao: ReadLaterDao
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
            return@withContext networkRequest.articles
                .asSequence()
                .filter(Article::isValid)
                .map(Article::toItemDTO)
                .toList()
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

    override suspend fun toggleBookmark(item: ListItem, isReadLater: Boolean) {
        val readLaterItem = ReadLaterItem(
            title = item.title!!,
            url = item.url!!,
            description = item.description,
            publishedAt = item.publishedAt,
            thumbnail = item.thumbnail,
            author = item.author,
            source = item.source
        )
        if (!isReadLater) {
            readLaterDao.upsert(readLaterItem)
        } else {
            readLaterDao.delete(readLaterItem)
        }
    }

    override suspend fun getBookmarks(): List<ListItem> {
        val readLaterItems = readLaterDao.getAll()
        return readLaterItems.map { readLater ->
            ListItem(
                title = readLater.title,
                url = readLater.url,
                description = readLater.description,
                publishedAt = readLater.publishedAt,
                thumbnail = readLater.thumbnail,
                author = readLater.author,
                source = readLater.source
            )
        }
    }
}