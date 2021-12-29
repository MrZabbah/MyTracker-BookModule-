package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository

class UpdateUserBookUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(book: Book) {
        repository.insertToUserBooks(book)
    }
}