package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components.DefaultCheckBox
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.ImageDisplayer
import com.mrzabbah.mytracker.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@Composable
fun SpecificBookScreen(
    navController: NavController,
    viewModel: SpecificBookViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SpecificBookViewModel.UiEvent.ShowSnackbar -> {
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
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(SpecificBookEvent.ToggleLabelSection)
                        },
                    ) {
                        Icon(
                            imageVector = if (state.book == null || state.book.label == Book.nonLabelColor)
                                Icons.Filled.BookmarkBorder
                            else
                                Icons.Filled.Bookmark,
                            contentDescription = "Label",
                            tint = Color(state.book?.label ?: Book.nonLabelColor)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                AnimatedVisibility(
                    visible = state.isFilterLabelToggled,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            DefaultCheckBox(
                                text = "TO_BUY",
                                color = RedOrange,
                                checked = state.book?.label == RedOrange.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(RedOrange.toArgb()))
                                }
                            )

                            DefaultCheckBox(
                                text = "PRGRMMNG",
                                color = LightGreen,
                                checked = state.book?.label == LightGreen.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(LightGreen.toArgb()))
                                }
                            )
                            DefaultCheckBox(
                                text = "FANTASY",
                                color = Violet,
                                checked = state.book?.label == Violet.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(Violet.toArgb()))
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            DefaultCheckBox(
                                text = "CASUAL",
                                color = BabyBlue,
                                checked = state.book?.label == BabyBlue.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(BabyBlue.toArgb()))
                                }
                            )
                            DefaultCheckBox(
                                text = "SCI-FY",
                                color = RedPink,
                                checked = state.book?.label == RedPink.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(RedPink.toArgb()))
                                }
                            )
                            DefaultCheckBox(
                                text = "HORROR",
                                color = Ruby,
                                checked = state.book?.label == Ruby.toArgb(),
                                onCheck = {
                                    viewModel.onEvent(SpecificBookEvent.FilterLabel(Ruby.toArgb()))
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ImageDisplayer(book = state.book ?: Book(""))
                    Column() {
                        Text(
                            text = state.book?.title ?: "",
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = state.book?.authors?.get(0) ?: "",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            text = state.book?.publisher ?: "",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}