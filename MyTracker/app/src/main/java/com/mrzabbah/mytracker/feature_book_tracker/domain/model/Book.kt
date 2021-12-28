package com.mrzabbah.mytracker.feature_book_tracker.domain.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrzabbah.mytracker.ui.theme.*

@Entity
data class Book(
    @PrimaryKey val id: Int? = null,
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val categories: List<String>?,
    val thumbnail: String?,
    val pageCount: Int? = null,
    val description: String?,
    var bitmapByteArray: ByteArray? = null,
    var timestamp: Long,
    val label: Int = LightGray.toArgb()
) {
    companion object {
        val bookLabels = listOf(RedOrange.toArgb(), LightGreen.toArgb(), Violet.toArgb(), BabyBlue.toArgb(), RedPink.toArgb(), Ruby.toArgb(), LightGray.toArgb())
    }

}
//parameter("fields","items(volumeInfo(title,subtitle,authors,publisher,categories,imageLinks(thumbnail),pageCount,description))")
class InvalidBookException(message: String): Exception(message)