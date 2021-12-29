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
    @PrimaryKey val id: String,
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val categories: List<String>?,
    val thumbnail: String?,
    val pageCount: Int? = null,
    val currentPage: Int = 0,
    val readTime: Long = 0,
    val description: String?,
    var bitmapByteArray: ByteArray? = null,
    var timestamp: Long,
    val label: Int = LightGray.toArgb()
) {
    companion object {
        val bookLabels = listOf(RedOrange.toArgb(), LightGreen.toArgb(), Violet.toArgb(), BabyBlue.toArgb(), RedPink.toArgb(), Ruby.toArgb(), LightGray.toArgb())
    }
}

class InvalidBookException(message: String): Exception(message)