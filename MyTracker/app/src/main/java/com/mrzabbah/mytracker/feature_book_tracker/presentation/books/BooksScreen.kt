package com.mrzabbah.mytracker.feature_book_tracker.presentation.books

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.BookItem
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components.FilterSection
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components.OrderSection
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.DefaultSearchBar
import com.mrzabbah.mytracker.feature_book_tracker.presentation.util.Screen
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun BooksScreen(
    navController: NavController,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DefaultSearchBar(
                onDone = { query ->
                    if (query.isNotBlank())
                        navController.navigate(Screen.SearchScreen.route + "/$query")
                },
                clear = true
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your books",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.width(64.dp))
                IconButton(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.onEvent(BooksEvent.ToggleUserOptionSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
                IconButton(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.onEvent(BooksEvent.ToggleFilterSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = "Filter"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isUserOptionSectionVisible || state.isFilterSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                if (state.isUserOptionSectionVisible) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        bookOrder = state.bookOrder,
                        onOrderChange = {
                            viewModel.onEvent(BooksEvent.Order(it))
                        }
                    )
                }
                if (state.isFilterSectionVisible) {
                    FilterSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        authorFilter = state.isAuthorFilterActive,
                        labelFilter = state.isLabelFilterActive,
                        labelsSelected = state.labelsSelected,
                        onCheckAuthorFilter = {
                            viewModel.onEvent(BooksEvent.ToggleAuthorFilter)
                        },
                        onCheckLabelFilter = {
                            viewModel.onEvent(BooksEvent.ToggleLabelFilter)
                        },
                        onCheckSpecificLabel = {
                            viewModel.onEvent(BooksEvent.FilterLabel(it))
                        }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.books) { book ->
                    BookItem(
                        book = book,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                focusManager.clearFocus()
                            },
                        onButtonOptionClick = {
                            focusManager.clearFocus()
                            viewModel.onEvent(BooksEvent.DeleteUserBook(book))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Book deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed)
                                    viewModel.onEvent(BooksEvent.RestoreUserBook)
                            }
                        },
                        buttonIcon = Icons.Default.Delete,
                        buttonDescription = "Delete user book"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
@Preview
fun Test() {
        Icon(
            imageVector = Icons.Default.FilterAlt,
            contentDescription = "Filter"
        )
}