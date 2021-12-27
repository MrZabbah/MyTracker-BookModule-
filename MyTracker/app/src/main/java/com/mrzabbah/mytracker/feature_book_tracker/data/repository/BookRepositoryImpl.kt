package com.mrzabbah.mytracker.feature_book_tracker.data.repository

import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.GoogleBooksService
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.BookSearchDto
import com.mrzabbah.mytracker.feature_book_tracker.data.data_source.BookDao
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val dao: BookDao,
    private val api: GoogleBooksService
): BookRepository {

    override fun getUserBooks(author: String?, labels: List<Int>): Flow<List<Book>> {
        return dao.getUserBooks(author, labels)
    }

    override suspend fun getUserBookById(id: Int): Book? {
        return dao.getUserBookById(id)
    }

    override suspend fun insertToUserBooks(book: Book) {
        dao.insertToUserBooks(book)
    }

    override suspend fun deleteUserBook(book: Book) {
        dao.deleteUserBook(book)
    }

    override suspend fun getBookSearch(title: String?, author: String?): BookSearchDto {
        return api.getBookSearch(title, author)
    }

    override suspend fun getThumbnail(url: String): ByteArray {
        return api.getThumbnail(url)
    }
}