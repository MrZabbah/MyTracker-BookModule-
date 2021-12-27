package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.BookOrder
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserBooksUseCase(
    private val repository: BookRepository
) {

    operator fun invoke(
        bookOrder: BookOrder = BookOrder.Date(OrderType.Descending),
        author: String? = null,
        labels: List<Int> = Book.bookLabels
    ): Flow<List<Book>> {
        return repository.getUserBooks(author, labels).map { books ->
            when(bookOrder.orderType) {
                is OrderType.Ascending -> {
                    when(bookOrder) {
                        is BookOrder.Author -> books.sortedBy { it.authors?.get(0)?.lowercase() }
                        is BookOrder.Date -> books.sortedBy { it.timestamp }
                        is BookOrder.Label -> books.sortedBy { it.label }
                    }
                }
                is OrderType.Descending -> {
                    when(bookOrder) {
                        is BookOrder.Author -> books.sortedByDescending { it.authors?.get(0)?.lowercase() }
                        is BookOrder.Date -> books.sortedByDescending { it.timestamp }
                        is BookOrder.Label -> books.sortedByDescending { it.label }
                    }
                }
            }
        }
    }
}