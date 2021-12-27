package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val finskyOfferType: Int?,
    val giftable: Boolean?,
    val listPrice: ListPriceX?,
    val retailPrice: RetailPrice?
)