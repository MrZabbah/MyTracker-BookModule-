package com.mrzabbah.mytracker.feature_book_tracker.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.ui.theme.DarkGray
import com.mrzabbah.mytracker.ui.theme.LightBlue
import com.mrzabbah.mytracker.ui.theme.LightGray

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onButtonOptionClick: () -> Unit,
    buttonIcon: ImageVector,
    buttonDescription: String
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(Book.nonLabelColor),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        BookInfoItem(book) {
            IconButton(
                onClick = onButtonOptionClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = buttonIcon,
                    contentDescription = buttonDescription
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.TopEnd),
                enabled = false
            ) {
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = "Label mark",
                    tint = if (book.label == Book.nonLabelColor || book.addedTimestamp <= 0L)
                        Color.Transparent
                    else
                        Color(book.label)
                )
            }
        }
    }
}