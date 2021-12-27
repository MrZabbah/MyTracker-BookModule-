package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    val acsTokenLink: String?,
    val downloadLink: String?,
    val isAvailable: Boolean?
)