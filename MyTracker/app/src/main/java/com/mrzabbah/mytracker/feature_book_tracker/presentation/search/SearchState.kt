package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book

data class SearchState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String = "",
    val lastSearch: String = ""
)