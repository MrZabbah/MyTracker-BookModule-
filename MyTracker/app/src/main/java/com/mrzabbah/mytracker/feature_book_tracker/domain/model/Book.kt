package com.mrzabbah.mytracker.feature_book_tracker.domain.model

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrzabbah.mytracker.ui.theme.*
import kotlinx.serialization.Serializable

@Serializable
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
    val readTime: Long = 0L,
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

    fun getHoursReadedString(): String {
        return "${(readTime/1000/3600)}Hrs : ${(readTime - ((readTime/1000/3600)*3600 *1000))/1000/60}Min"
    }

    fun getReadedPercentage(): Float {
        return pageCount?.let { pageCount ->
            currentPage.toFloat() / pageCount
        } ?: 0f
    }
}

class InvalidBookException(message: String): Exception(message)