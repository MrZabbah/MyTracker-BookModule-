package com.mrzabbah.mytracker.feature_book_tracker.domain.repository

import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.BookSearchDto
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.Item
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    //DB
    fun getUserBooks(author: String?, labels: List<Int>): Flow<List<Book>>

    suspend fun getUserBookById(id: String): Book?

    suspend fun insertToUserBooks(book: Book)

    suspend fun deleteUserBook(book: Book)

    //API
    suspend fun getBookSearch(title: String? = null, author: String? = null): BookSearchDto

    suspend fun getBookByRemoteId(id: String): Item

    suspend fun getThumbnail(url: String): ByteArray
}