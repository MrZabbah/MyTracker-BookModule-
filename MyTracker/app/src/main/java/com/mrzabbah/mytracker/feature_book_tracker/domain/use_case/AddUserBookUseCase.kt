package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.InvalidBookException
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository

class AddUserBookUseCase(
    private val repository: BookRepository
) {

    @Throws(InvalidBookException::class)
    suspend operator fun invoke(book: Book) {
        if (book.id.isBlank())
            throw InvalidBookException("This book is corrupted. Please, try to reload the search")
        if (book.title == null || book.title.isBlank())
            throw InvalidBookException("This book has no title. Please, try to reload the search")
        if (book.pageCount == null || book.pageCount <= 0)
            throw InvalidBookException("This book has no page count. Please, try to reload the search")

        book.addedTimestamp = if (book.addedTimestamp > 0) book.addedTimestamp else System.currentTimeMillis()

        // Comprobar que no esta ya guardado
        repository.getUserBookById(book.id)?.let {
            throw InvalidBookException("This book is already on \"Your Books\"")
        }
        val detailedBookInfo = repository.getBookByRemoteId(book.id)

        val detailBook = book.copy(
            authors = detailedBookInfo.volumeInfo?.authors ?: book.authors,
            categories = detailedBookInfo.volumeInfo?.categories ?: book.categories
        )

        repository.insertToUserBooks(detailBook)
    }
}