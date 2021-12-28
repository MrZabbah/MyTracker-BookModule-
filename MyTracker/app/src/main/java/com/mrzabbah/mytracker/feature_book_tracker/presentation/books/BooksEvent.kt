package com.mrzabbah.mytracker.feature_book_tracker.presentation.books

import androidx.compose.ui.focus.FocusState
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.BookOrder

sealed class BooksEvent {
    data class Order(val bookOrder: BookOrder): BooksEvent()
    data class FilterAuthor(val author: String?): BooksEvent()
    data class FilterLabel(val label: Int): BooksEvent()
    data class DeleteUserBook(val book: Book): BooksEvent()
    data class EnteredSearch(val value: String): BooksEvent()
    data class ChangeSearchFocus(val focusState: FocusState): BooksEvent()
    object RestoreUserBook: BooksEvent()
    object ToggleUserOptionSection: BooksEvent()
    object ToggleFilterSection: BooksEvent()
    object ToggleLabelFilter: BooksEvent()
    object ToggleAuthorFilter: BooksEvent()
}
