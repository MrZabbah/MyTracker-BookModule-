package com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.BookOrder
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    bookOrder: BookOrder = BookOrder.Date(OrderType.Descending),
    onOrderChange: (BookOrder) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Date",
                selected = bookOrder is BookOrder.Date,
                onSelect = { onOrderChange(BookOrder.Date(bookOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Author",
                selected = bookOrder is BookOrder.Author,
                onSelect = { onOrderChange(BookOrder.Author(bookOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Label",
                selected = bookOrder is BookOrder.Label,
                onSelect = { onOrderChange(BookOrder.Label(bookOrder.orderType))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = bookOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(bookOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = bookOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(bookOrder.copy(OrderType.Descending))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}