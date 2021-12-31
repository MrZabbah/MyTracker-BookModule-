package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook

import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components.DefaultCheckBox
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.ImageDisplayer
import com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.components.CircularProgressBar
import com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.components.DefaultAlertDialog
import com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.components.DefaultInfoDisplayer
import com.mrzabbah.mytracker.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SpecificBookScreen(
    navController: NavController,
    viewModel: SpecificBookViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( vertical = 4.dp)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier.align(alignment = Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back",
                            tint = LightGray
                        )
                    }
                    if (state.book != null && state.book.addedTimestamp > 0) {
                        Row(
                            modifier = Modifier.align(alignment = Alignment.CenterEnd)
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(SpecificBookEvent.ToggleActive)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MenuBook,
                                    contentDescription = "Active book",
                                    tint = if (state.book.active) CasualBlue else LightGray
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(SpecificBookEvent.ToggleLabelSection)
                                }
                            ) {
                                Icon(
                                    imageVector = if (state.book.label == Book.nonLabelColor)
                                        Icons.Filled.BookmarkBorder
                                    else
                                        Icons.Filled.Bookmark,
                                    contentDescription = "Label",
                                    tint = Color(state.book.label)
                                )
                            }
                        }
                    }
                }
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
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ImageDisplayer(
                        book = state.book ?: Book(""),
                        modifier = Modifier
                            .height(192.dp)
                            .width(144.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(24.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column() {
                        Text(
                            text = state.book?.title ?: "",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = state.book?.authors?.get(0) ?: "",
                            style = MaterialTheme.typography.subtitle1,
                            color = CasualBlue
                        )
                        Text(
                            text = state.book?.publisher ?: "",
                            style = MaterialTheme.typography.subtitle1,
                            color = LightGray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (state.book != null && state.book.addedTimestamp > 0) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DefaultInfoDisplayer(
                                value = state.book.getHoursReadedString(),
                                info = "Reading"
                            )
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(25.dp)
                                    .background(color = LightGray)
                            ) {}
                            CircularProgressBar(
                                percentage = state.book.getReadedPercentage(),
                                fontSize = 16.sp,
                                radius = 24.dp,
                                strokeWidth = 4.dp
                            )
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(25.dp)
                                    .background(color = LightGray)
                            ) {}
                            DefaultInfoDisplayer(
                                value = "${state.book.currentPage}/${state.book.pageCount}",
                                info = "Pages"
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    viewModel.onEvent(SpecificBookEvent.ClickReadButton)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = CasualBlue
                                )
                            ) {
                                Text(text = state.book.lastReadTimestamp?.let { "Stop reading" }
                                    ?: "Start reading", color = MaterialTheme.colors.onSurface)
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Button(
                                onClick = {
                                    viewModel.onEvent(SpecificBookEvent.TogglePagesDialog)
                                },
                                modifier = Modifier
                                    .weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = state.book.lastReadTimestamp?.let { LightGray }
                                        ?: CasualBlue
                                ),
                                enabled = state.book.lastReadTimestamp?.let { false }
                                    ?: true
                            ) {
                                Text(
                                    text = "Set current page",
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "About the book",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                    ) {
                        state.book?.subtitle?.let { subtitle ->
                            Text(
                                text = subtitle,
                                style = MaterialTheme.typography.subtitle1,
                                textAlign = TextAlign.Center,
                                color = LightGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        state.book?.description?.let { description ->
                            Text(
                                text = "\t $description",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
        if (state.isDialogToggled) {

            DefaultAlertDialog(
                onDismiss = { viewModel.onEvent(SpecificBookEvent.TogglePagesDialog) },
                onPositiveClick = {
                    viewModel.onEvent(SpecificBookEvent.ClickOnConfirmPage(it))
                    viewModel.onEvent(SpecificBookEvent.TogglePagesDialog)
                },
                onNegativeClick = { viewModel.onEvent(SpecificBookEvent.TogglePagesDialog) }
            )
        }
    }
}