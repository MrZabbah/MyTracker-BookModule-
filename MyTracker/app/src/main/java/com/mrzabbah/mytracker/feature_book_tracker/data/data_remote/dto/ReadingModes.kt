package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    val image: Boolean?,
    val text: Boolean?
)