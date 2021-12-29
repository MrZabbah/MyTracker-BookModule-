package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.core.util.Resource
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.InvalidQueryException
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.SearchMode
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception


class GetBookSearchUseCase(
    private val repository: BookRepository
) {

    operator fun invoke(
        searchString: String?, searchMode: SearchMode
    ): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val queryParams = getQueryParams(searchString, searchMode)
            val books =
                repository.getBookSearch(queryParams[0], queryParams[1]).items.map { it.toBook() }
            books.onEach { book ->
                book.thumbnail?.let {
                    book.bitmapByteArray = repository.getThumbnail(it)
                }
            }
            emit(Resource.Success(books))
        } catch (e: InvalidQueryException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your internet connection"))
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: ClientRequestException) {
            // 4xx - responses
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: ServerResponseException) {
            //5xx - responses
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error("Not results found." ?: "An unexpected error occurred"))
        }
    }

    private fun getQueryParams(rawSearch: String?, searchMode: SearchMode): List<String?> {
        return when (searchMode) {
            SearchMode.Advanced -> {
                val list = mutableListOf<String?>()
                rawSearch?.split("|")?.onEach {
                    if (it.isNotBlank()) {
                        val string = cleanString(it)
                        list.add(string)
                    }
                    else
                        list.add(null)
                }
                if (list.isEmpty()) listOf(null, null) else list
            }
            SearchMode.ByAuthor -> {
                listOf(null, rawSearch?.let { cleanString(it) })
            }
            SearchMode.ByTitle -> {
                listOf(rawSearch?.let { cleanString(it) }, null)
            }
        }
    }

    private fun cleanString(string: String): String {
        var cleanString = ""
        string.split("\\s+".toRegex()).onEach { word ->
            cleanString += if (word.isBlank() || cleanString.isBlank()) word else " $word"
        }
        return cleanString
    }
}