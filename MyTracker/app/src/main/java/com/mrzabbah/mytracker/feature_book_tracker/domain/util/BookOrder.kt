package com.mrzabbah.mytracker.feature_book_tracker.domain.util

sealed class BookOrder(val orderType: OrderType) {
    class  Author(orderType: OrderType): BookOrder(orderType)
    class  Date(orderType: OrderType): BookOrder(orderType)
    class  Label(orderType: OrderType): BookOrder(orderType)

    fun copy(orderType: OrderType): BookOrder {
        return  when(this) {
            is Author -> Author(orderType)
            is Date -> Date(orderType)
            is Label -> Label(orderType)
        }
    }
}
