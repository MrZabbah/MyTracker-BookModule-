package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)