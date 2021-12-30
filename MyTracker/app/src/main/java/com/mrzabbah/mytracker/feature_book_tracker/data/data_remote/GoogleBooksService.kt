package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote

import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.BookSearchDto
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.Item
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.ExperimentalSerializationApi

interface GoogleBooksService {

    @Throws(InvalidQueryException::class)
    suspend fun getBookSearch(title: String? = null, author: String? = null): BookSearchDto

    suspend fun getBookByRemoteId(id: String): Item

    suspend fun getThumbnail(url: String): ByteArray

    companion object {
        @ExperimentalSerializationApi
        fun create(): GoogleBooksService {
            val format = kotlinx.serialization.json.Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            }
            return GoogleBooksServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(format)
                    }
                    Charsets {
                        register(Charsets.US_ASCII)
                    }
                }
            )
        }
    }
}

class InvalidQueryException(message: String): Exception(message)