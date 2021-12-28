package com.mrzabbah.mytracker.feature_book_tracker.presentation.common

data class SearchTextFieldState(
    val text: String = "",
    val hint: String = "Search book",
    val isFocused: Boolean = false
)