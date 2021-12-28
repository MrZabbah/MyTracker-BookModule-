package com.mrzabbah.mytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.GoogleBooksService
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.dto.BookSearchDto
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.BooksScreen
import com.mrzabbah.mytracker.feature_book_tracker.presentation.common.DefaultSearchBar
import com.mrzabbah.mytracker.feature_book_tracker.presentation.search.SearchScreen
import com.mrzabbah.mytracker.feature_book_tracker.presentation.util.Screen
import com.mrzabbah.mytracker.ui.theme.MyTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    @ExperimentalSerializationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTrackerTheme {
                val focusManager = LocalFocusManager.current
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() } // This is mandatory
                        ) {
                            focusManager.clearFocus()
                        }
                ) {
                    val navController = rememberNavController()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //val search = DefaultSearchBar(navController = navController)
                        NavHost(
                            navController = navController,
                            startDestination = Screen.BooksScreen.route
                        ) {
                            composable(
                                route = Screen.BooksScreen.route
                            ) {
                                BooksScreen(navController = navController)
                            }
                            composable(
                                route = Screen.SearchScreen.route + "/{query}",
                                arguments = listOf(
                                    navArgument(
                                        name = "query"
                                    ) {
                                        type = NavType.StringType
                                        defaultValue = ""
                                    }
                                )
                            ) {
                                SearchScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }


    /*@ExperimentalSerializationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val books = produceState<BookSearchDto?>(
                initialValue = null,
                producer = {
                    value = service.getBookSearch("el codigo da vinci", null)
                }
            )

            MyTrackerTheme {
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true ) {

                }


                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Text(text = "COUNT: ${books.value?.items?.size}", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn{
                        books.value?.items?.let {
                            items(it){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    it.volumeInfo?.title?.let { it1 -> Text(text = it1, fontSize = 20.sp) }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    it.volumeInfo?.authors?.get(0)
                                        ?.let { it1 -> Text(text = it1, fontSize = 20.sp) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
}
