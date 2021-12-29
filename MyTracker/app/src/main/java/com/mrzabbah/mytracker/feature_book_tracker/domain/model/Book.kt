package com.mrzabbah.mytracker.feature_book_tracker.domain.model

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrzabbah.mytracker.ui.theme.*

@Entity
data class Book(
    @PrimaryKey val id: String = "",
    val title: String? = null,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val categories: List<String>? = null,
    val thumbnail: String? = null,
    val pageCount: Int? = null,
    val currentPage: Int = 0,
    val readTime: Long = 0,
    val lastReadTimestamp: Long? = null,
    val description: String? = null,
    var bitmapByteArray: ByteArray? = null,
    var addedTimestamp: Long = 0L,
    val label: Int = nonLabelColor
) {
    companion object {
        val nonLabelColor = LightGray.toArgb()
        val bookLabels = listOf(RedOrange.toArgb(), LightGreen.toArgb(), Violet.toArgb(), BabyBlue.toArgb(), RedPink.toArgb(), Ruby.toArgb(), LightGray.toArgb())
    }
}

class InvalidBookException(message: String): Exception(message)