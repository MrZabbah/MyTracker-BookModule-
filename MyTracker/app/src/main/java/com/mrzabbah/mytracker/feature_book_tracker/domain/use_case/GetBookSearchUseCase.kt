package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.mrzabbah.mytracker.core.util.Resource
import com.mrzabbah.mytracker.core.util.SimpleResource
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.InvalidQueryException
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception


class GetBookSearchUseCase(
    private val repository: BookRepository
) {

    operator fun invoke(
        title: String?, author: String?
    ): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val books = repository.getBookSearch(title, author).items.map { it.toBook() }
            books.onEach { book ->
                book.thumbnail?.let {
                    book.bitmapByteArray = repository.getThumbnail(it)
                }
            }
            emit(Resource.Success(books))
        } catch (e: InvalidQueryException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please check your internet connection"))
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
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}