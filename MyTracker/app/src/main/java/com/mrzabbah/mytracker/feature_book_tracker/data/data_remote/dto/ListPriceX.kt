package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ListPriceX(
    val amountInMicros: Int?,
    val currencyCode: String?
)