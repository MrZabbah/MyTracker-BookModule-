package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto


import android.content.res.Resources
import android.graphics.BitmapFactory
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val accessInfo: AccessInfo?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val searchInfo: SearchInfo?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
) {
    fun toBook(): Book {
        return Book(
            id = id?:"",
            title = volumeInfo?.title,
            subtitle = volumeInfo?.subtitle,
            authors = volumeInfo?.authors,
            publisher = volumeInfo?.publisher,
            categories = volumeInfo?.categories,
            thumbnail = volumeInfo?.imageLinks?.thumbnail,
            pageCount = volumeInfo?.pageCount,
            description = volumeInfo?.description,
            timestamp = 0L,
        )
    }
}