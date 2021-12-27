package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class BookSearchDto(
    val items: List<Item>,
    val kind: String?,
    val totalItems: Int
)