package com.mrzabbah.mytracker.feature_book_tracker.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
