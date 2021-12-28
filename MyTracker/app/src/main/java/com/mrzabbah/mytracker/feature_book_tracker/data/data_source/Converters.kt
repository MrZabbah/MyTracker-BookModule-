package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.TypeConverter
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.BookOrder

class Converters {

    @TypeConverter
    fun fromListToString(list: List<String>?): String? =
        list?.joinToString(separator = "*") { it }

    @TypeConverter
    fun fromStringToList(string: String?): MutableList<String>? =
        string?.split("*")?.map { it }?.toMutableList()

    @TypeConverter
    fun fromBookOrderToString(bookOrder: BookOrder): String =
        bookOrder.parseToString()

    @TypeConverter
    fun fromStringToBookOrder(value: String): BookOrder =
        BookOrder.parseToBookOrder(value)

    @TypeConverter
    fun fromListIntToString(list: List<Int>): String =
        list.joinToString(separator = "*") { it.toString() }

    @TypeConverter
    fun fromStringToListInt(string: String?): List<Int> {
        val list = string?.split("*")?.map { it.toInt() }?.toMutableList()
        return list ?: emptyList()
    }


    @TypeConverter
    fun fromByteArrayToString(byteArray: ByteArray): String? =
        android.util.Base64.encodeToString(byteArray, 0)

    @TypeConverter
    fun fromStringToByteArray(string: String?): ByteArray? =
        android.util.Base64.decode(string, 0)
}