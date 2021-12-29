package com.mrzabbah.mytracker.feature_book_tracker.domain.util

sealed class SearchMode {
    object ByTitle : SearchMode()
    object ByAuthor : SearchMode()
    object Advanced : SearchMode()

    override fun toString(): String {
        return when (this) {
            Advanced -> ADVANCE_HINT
            ByAuthor -> BY_AUTHOR_HINT
            ByTitle -> BY_TITLE_HINT
        }
    }

    companion object {
        const val BY_TITLE_HINT = "Search book"
        const val BY_AUTHOR_HINT = "Search author"
        const val ADVANCE_HINT = "Search book | author"

        fun parseFromString(value: String): SearchMode {
            return when {
                value.equals(BY_TITLE_HINT) -> ByTitle
                value.equals(BY_AUTHOR_HINT) -> ByAuthor
                else -> Advanced
            }
        }
    }
}