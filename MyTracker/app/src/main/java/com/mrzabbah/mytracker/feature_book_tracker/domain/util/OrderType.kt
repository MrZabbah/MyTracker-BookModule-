package com.mrzabbah.mytracker.feature_book_tracker.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()



    companion object {
        fun parseToOrderType(key: String): OrderType {
            return when {
                key.equals("asc") -> Ascending
                else -> Descending
            }
        }

        fun parseToString(orderType: OrderType): String {
            return when(orderType) {
                is Ascending -> "asc"
                is Descending -> "desc"
            }
        }
    }
}
