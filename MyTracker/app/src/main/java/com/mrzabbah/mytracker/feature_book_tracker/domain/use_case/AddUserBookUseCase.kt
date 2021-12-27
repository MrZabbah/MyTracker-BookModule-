package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.InvalidBookException
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository

class AddUserBookUseCase(
    private val repository: BookRepository
) {

    @Throws(InvalidBookException::class)
    suspend operator fun invoke(book: Book) {
        if (book.title == null || book.title.isBlank())
            throw InvalidBookException("This book has no title. Please, try to reload the search")
        if (book.pageCount == null || book.pageCount <= 0)
            throw InvalidBookException("This book has no page count. Please, try to reload the search")

        repository.insertToUserBooks(book)
    }
}