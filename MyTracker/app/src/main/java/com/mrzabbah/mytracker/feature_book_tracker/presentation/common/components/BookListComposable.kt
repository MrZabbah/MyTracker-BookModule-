package com.mrzabbah.mytracker.feature_book_tracker.presentation.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.BookItem
import com.mrzabbah.mytracker.feature_book_tracker.presentation.util.Screen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BookListComposable(
    books: List<Book> = emptyList(),
    focusManager: FocusManager,
    navController: NavController,
    onBookAction: (Book) -> Unit
) {

}