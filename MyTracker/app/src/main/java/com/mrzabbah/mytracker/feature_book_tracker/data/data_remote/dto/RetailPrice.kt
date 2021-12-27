package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class RetailPrice(
    val amountInMicros: Int?,
    val currencyCode: String?
)