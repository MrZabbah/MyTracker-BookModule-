package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.util

import java.lang.reflect.Type
//Contar en la memoria que es de cara a futuro cuando haya mas cosas aparte de librs y datos mas complejos que almacenar
interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(obj: T, type: Type): String?
}