package com.mrzabbah.mytracker.feature_book_tracker.presentation.common

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.ui.theme.Gray

@Composable
fun ImageDisplayer(
    book: Book,
    modifier: Modifier = Modifier
) {
    val o = LocalContext.current.resources
    val imageBitmap =  remember {
            book.bitmapByteArray?.let {
            BitmapFactory.decodeByteArray(book.bitmapByteArray,0, it.size).asImageBitmap()
        } ?: BitmapFactory.decodeResource(o, com.mrzabbah.mytracker.R.drawable.non_available).asImageBitmap()

    }
    Image(
        bitmap = imageBitmap,
        contentDescription = "Thumbnail",
        modifier = modifier
    )
}

@Composable
fun BookInfoItem(
    book: Book,
    optionButton: @Composable
        () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ImageDisplayer(
            book = book,
            modifier = Modifier
                .height(142.5.dp)
                .width(96.dp)
                .align(CenterVertically)
                .padding(8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Top)
                .padding(4.dp)
                .padding(end = 32.dp)
        ) {
            val title = book.title ?: "Unknown title"
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            book.subtitle?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            //Spacer(modifier = Modifier.height(4.dp))
            val author = book.authors ?: listOf("Unknown")
            Text(
                text = "Author: ${author[0]}" ,
                style = MaterialTheme.typography.subtitle2,
                color = Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            //Spacer(modifier = Modifier.height(4.dp))
            book.publisher?.let {
                Text(
                    text = "Publisher: $it",
                    style = MaterialTheme.typography.subtitle2,
                    color = Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                //Spacer(modifier = Modifier.height(4.dp))
            }
            book.categories?.let {
                Text(
                    text = "Main category: ${it[0]}",
                    style = MaterialTheme.typography.subtitle2,
                    color = Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                //Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
    optionButton()
}

