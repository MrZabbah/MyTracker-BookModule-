package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(list: List<String>?): String? =
        list?.joinToString(separator = "*") { it }

    @TypeConverter
    fun fromStringToList(string: String?): MutableList<String>? =
        string?.split("*")?.map { it }?.toMutableList()

    @TypeConverter
    fun fromByteArrayToString(byteArray: ByteArray): String? =
        android.util.Base64.encodeToString(byteArray, 0)

    @TypeConverter
    fun fromStringToByteArray(string: String?): ByteArray? =
        android.util.Base64.decode(string, 0)
}