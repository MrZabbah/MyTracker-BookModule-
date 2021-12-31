package com.mrzabbah.mytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mrzabbah.mytracker.feature_book_tracker.presentation.books.BooksScreen
import com.mrzabbah.mytracker.feature_book_tracker.presentation.search.SearchScreen
import com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.SpecificBookScreen
import com.mrzabbah.mytracker.feature_book_tracker.presentation.util.Screen
import com.mrzabbah.mytracker.ui.theme.MyTrackerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor =  Color.Transparent.toArgb()

        setContent {
            MyTrackerTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    val focusManager = LocalFocusManager.current
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() } // This is mandatory
                            ) {
                                focusManager.clearFocus()
                            }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.BooksScreen.route
                        ) {
                            composable(
                                route = Screen.BooksScreen.route
                            ) {
                                BooksScreen(
                                    navController = navController,
                                    focusManager = focusManager
                                )
                            }
                            composable(
                                route = Screen.SearchScreen.route +
                                        "/{query}/{searchMode}",
                                arguments = listOf(
                                    navArgument(
                                        name = "query"
                                    ) {
                                        type = NavType.StringType
                                        defaultValue = ""
                                    },
                                    navArgument(
                                        name = "searchMode"
                                    ) {
                                        type = NavType.StringType
                                        defaultValue = ""
                                    }
                                )
                            ) {
                                SearchScreen(
                                    navController = navController,
                                    focusManager = focusManager
                                )
                            }
                            composable(
                                route = Screen.SpecificBookScreen.route +
                                        "/{id}",
                                arguments = listOf(
                                    navArgument(
                                        name = "id"
                                    ) {
                                        type = NavType.StringType
                                        defaultValue = ""
                                    }
                                )
                            ) {
                                SpecificBookScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
