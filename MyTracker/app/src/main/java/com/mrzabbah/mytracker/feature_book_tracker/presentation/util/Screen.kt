package com.mrzabbah.mytracker.feature_book_tracker.presentation.util

sealed class Screen(val route: String) {
    object BooksScreen: Screen("books_screen")
    object SearchScreen: Screen("search_screen")
}
