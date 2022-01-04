package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.BooksEvent
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.BookItem
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.DefaultSearchBar
import com.mrzabbah.mytracker.feature_book_tracker.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    focusManager: FocusManager
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SearchViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                DefaultSearchBar(
                    onDone = { query ->
                        viewModel.onEvent(SearchEvent.Search(query))
                    },
                    initialSearch = state.lastSearch,
                    clear = false,
                    focusManager = focusManager,
                    onChangeClick = { viewModel.onEvent(SearchEvent.ChangeSearchMode) },
                    searchMode = state.searchMode
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    items(state.books) { book ->
                        BookItem(
                            book = book,
                            onButtonOptionClick = {
                                viewModel.onEvent(SearchEvent.SaveBook(book))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.SpecificBookScreen.route +
                                                "/${
                                                    URLEncoder.encode(
                                                        Json.encodeToString(book),
                                                        StandardCharsets.UTF_8.toString()
                                                    )
                                                }"
                                    )
                                },
                            buttonIcon = Icons.Default.Add,
                            buttonDescription = "Add book to bookshelf"
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview
@Composable
fun test() {
    Icon(
        imageVector = Icons.Filled.MoreVert,
        contentDescription = "",
        tint = Color(Book.nonLabelColor)
    )
}