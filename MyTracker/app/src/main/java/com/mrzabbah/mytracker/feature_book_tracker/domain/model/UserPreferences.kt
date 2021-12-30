package com.mrzabbah.mytracker.feature_book_tracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.BookOrder
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.OrderType

@Entity
data class UserPreferences(
    @PrimaryKey val key: String = NAME,
    val bookOrder: BookOrder = BookOrder.Date(OrderType.Descending),
    val authorSelected: String? = null,
    val labelsSelected: List<Int> = Book.bookLabels,
    val labelsOnFilter: List<Int> = emptyList(),
    val isAuthorFilterActive: Boolean = false,
    val isLabelFilterActive: Boolean = false
) {
    companion object {
        const val NAME = "user_preferences"
    }
}
