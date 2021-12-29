package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book

sealed class SearchEvent {
    data class Search(val title: String?): SearchEvent()
    data class SaveBook(val book: Book): SearchEvent()
    object ChangeSearchMode: SearchEvent()
}
