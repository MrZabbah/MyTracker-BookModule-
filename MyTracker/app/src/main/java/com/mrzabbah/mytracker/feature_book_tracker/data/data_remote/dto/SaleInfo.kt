package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    val buyLink: String?,
    val country: String?,
    val isEbook: Boolean?,
    val listPrice: ListPrice?,
    val offers: List<Offer>?,
    val retailPrice: RetailPriceX?,
    val saleability: String?
)