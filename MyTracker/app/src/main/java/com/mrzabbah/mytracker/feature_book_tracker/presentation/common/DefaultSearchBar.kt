package com.mrzabbah.mytracker.feature_book_tracker.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrzabbah.mytracker.ui.theme.CasualBlue
import com.mrzabbah.mytracker.ui.theme.Gray
import com.mrzabbah.mytracker.ui.theme.LightGray

@ExperimentalComposeUiApi
@Composable
fun DefaultSearchBar(
    onDone: (String) -> Unit,
    initialSearch: String = "",
    clear: Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue(initialSearch)) }
    val colorBackground = remember { mutableStateOf(Gray) }
    val colorOnBackground = remember { mutableStateOf(LightGray) }
    val colorText = remember { mutableStateOf(CasualBlue) }
    val focusState = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            placeholder = {
                Text(text = "Search book", color = LightGray)
            },
            textStyle = TextStyle(
                color = colorText.value,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .background(color = colorBackground.value, shape = RoundedCornerShape(60.dp))
                .border(
                    width = 1.dp,
                    color = colorOnBackground.value,
                    shape = RoundedCornerShape(60.dp)
                )
                .height(54.dp)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        colorText.value = CasualBlue
                        colorBackground.value = Color.Transparent
                        colorOnBackground.value = Color.Transparent
                    } else {
                        colorText.value = Color.LightGray
                        colorBackground.value = Gray
                        colorOnBackground.value = LightGray
                    }
                },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = CasualBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon",
                    tint = colorText.value
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone(textState.value.text)
                    if (clear) textState.value = TextFieldValue("")
                    focusState.clearFocus()
                },
            )
        )
    }
}
