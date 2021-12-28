package com.mrzabbah.mytracker.feature_book_tracker.domain.util

sealed class BookOrder(val orderType: OrderType) {
    class Author(orderType: OrderType) : BookOrder(orderType)
    class Date(orderType: OrderType) : BookOrder(orderType)
    class Label(orderType: OrderType) : BookOrder(orderType)

    fun copy(orderType: OrderType): BookOrder {
        return when (this) {
            is Author -> Author(orderType)
            is Date -> Date(orderType)
            is Label -> Label(orderType)
        }
    }

    fun parseToString(): String {
        return when (this) {
            is Author -> "author|${OrderType.parseToString(orderType)}"
            is Date -> "date|${OrderType.parseToString(orderType)}"
            is Label -> "label|${OrderType.parseToString(orderType)}"
        }
    }

    companion object {
        fun parseToBookOrder(value: String): BookOrder {
            val keys = value.split("|")
            return when {
                keys[0].equals("author") -> Author(OrderType.parseToOrderType(keys[1]))
                keys[0].equals("date") -> Date(OrderType.parseToOrderType(keys[1]))
                else -> Label(OrderType.parseToOrderType(keys[1]))
            }
        }
    }
}
