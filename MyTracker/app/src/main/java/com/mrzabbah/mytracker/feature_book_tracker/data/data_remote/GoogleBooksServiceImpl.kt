package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote

import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.BookSearchDto
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.Item
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.util.HttpRoutes
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.lang.Exception

class GoogleBooksServiceImpl(
    private val client: HttpClient
) : GoogleBooksService {

    override suspend fun getBookSearch(title: String?, author: String?): BookSearchDto {
        var query = ""
        title?.let {
            if (title.isNotBlank())
                query = query.plus("intitle:${title.lowercase()}")
        }
        author?.let {
            if (author.isNotBlank()) {
                if (title != null && title.isNotBlank())
                    query = query.plus("+")
                query = query.plus("inauthor:${author.lowercase()}")
            }
        }

        if (query.isBlank())
            throw InvalidQueryException("There is no query. Please insert one before searching.")

        return client.get(scheme = "https") {
            url(HttpRoutes.VOLUMES)
            parameter("q", query)
            parameter("maxResults", 40)
            parameter(
                "fields",
                "totalItems,items(id,volumeInfo(title,subtitle,authors,publisher,categories,imageLinks,pageCount,description))"
            )
        }
    }

    override suspend fun getBookByRemoteId(id: String): Item {
        return client.get(scheme = "https") {
            url("${HttpRoutes.VOLUMES}/$id")
            parameter(
                "fields",
                "volumeInfo(authors,categories)"
            )
        }
    }

    override suspend fun getThumbnail(url: String): ByteArray {
        return client.get(scheme = "https") {
            url(url)
        }
    }
}