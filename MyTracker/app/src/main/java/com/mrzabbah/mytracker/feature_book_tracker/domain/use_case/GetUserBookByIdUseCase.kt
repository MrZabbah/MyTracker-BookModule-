package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.InvalidBookException
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository

class GetUserBookByIdUseCase(
    private val repository: BookRepository
) {
    @Throws(InvalidBookException::class)
    suspend operator fun invoke(id: String): Book {
        return repository.getUserBookById(id) ?: throw InvalidBookException("Book could not be loaded")
    }
}