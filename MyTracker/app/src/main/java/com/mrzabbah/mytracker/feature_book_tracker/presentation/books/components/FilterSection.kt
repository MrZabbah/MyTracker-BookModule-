package com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.mrzabbah.mytracker.ui.theme.*

@ExperimentalAnimationApi
@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    authorFilter: Boolean,
    labelFilter: Boolean,
    labelsSelected: List<Int>,
    onCheckAuthorFilter: (Boolean) -> Unit,
    onCheckLabelFilter: (Boolean) -> Unit,
    onCheckSpecificLabel: (Int) -> Unit,
    focusManager: FocusManager
) {
    Column(
        modifier = modifier
    ) {
//        DefaultSwitch(
//            text = "Author filter",
//            checked = authorFilter,
//            onCheck = {
//                focusManager.clearFocus()
//                onCheckAuthorFilter(it)
//            }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
        DefaultSwitch(
            text = "Label filter",
            checked = labelFilter,
            onCheck = {
                focusManager.clearFocus()
                onCheckLabelFilter(it)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(
            visible = labelFilter,
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
                        checked = labelsSelected.contains(RedOrange.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(RedOrange.toArgb())
                        }
                    )

                    DefaultCheckBox(
                        text = "PRGRMMNG",
                        color = LightGreen,
                        checked = labelsSelected.contains(LightGreen.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(LightGreen.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "FANTASY",
                        color = Violet,
                        checked = labelsSelected.contains(Violet.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(Violet.toArgb())
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
                        checked = labelsSelected.contains(BabyBlue.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(BabyBlue.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "SCI-FY",
                        color = RedPink,
                        checked = labelsSelected.contains(RedPink.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(RedPink.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "HORROR",
                        color = Ruby,
                        checked = labelsSelected.contains(Ruby.toArgb()),
                        onCheck = {
                            focusManager.clearFocus()
                            onCheckSpecificLabel(Ruby.toArgb())
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}