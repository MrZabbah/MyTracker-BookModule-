package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book

data class SpecificBookState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val isFilterLabelToggled: Boolean = false,
    val isDialogToggled: Boolean = false,
    val isMoreActionToggled: Boolean = false
)
