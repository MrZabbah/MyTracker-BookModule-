package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mrzabbah.mytracker.ui.theme.CasualBlue

@ExperimentalComposeUiApi
@Composable
fun DefaultAlertDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Int) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val requester = FocusRequester()

    LaunchedEffect(key1 = true) {
        requester.requestFocus()
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = MaterialTheme.colors.onSurface
        ) {

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Set current page",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = CasualBlue,
                        focusedIndicatorColor = CasualBlue,
                        unfocusedIndicatorColor = CasualBlue,
                        textColor = CasualBlue
                    ),
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .focusRequester(requester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onPositiveClick(
                                try {
                                    text.toInt()
                                } catch (e: Exception) {
                                    -1
                                }
                            )
                        }
                    ),
                )

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = onNegativeClick) {
                        Text(text = "CANCEL", color = CasualBlue)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        onPositiveClick(if (text.isNotBlank()) text.toInt() else -1)
                    }) {
                        Text(text = "OK", color = CasualBlue)
                    }
                }
            }
        }
    }
}