package com.mrzabbah.mytracker.feature_book_tracker.presentation.books.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onCheckSpecificLabel: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DefaultSwitch(
            text = "Author filter",
            checked = authorFilter,
            onCheck = {
                onCheckAuthorFilter(it)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DefaultSwitch(
            text = "Label filter",
            checked = labelFilter,
            onCheck = {
                onCheckLabelFilter(it)
            }
        )
        AnimatedVisibility(
            visible = labelFilter,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    DefaultCheckBox(
                        text = "URGENT",
                        color = RedOrange,
                        checked = labelsSelected.contains(RedOrange.toArgb()),
                        onCheck = {
                            onCheckSpecificLabel(RedOrange.toArgb())
                        }
                    )

                    DefaultCheckBox(
                        text = "PRGRMMNG",
                        color = LightGreen,
                        checked = labelsSelected.contains(LightGreen.toArgb()),
                        onCheck = {
                            onCheckSpecificLabel(LightGreen.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "FANTASY",
                        color = Violet,
                        checked = labelsSelected.contains(Violet.toArgb()),
                        onCheck = {
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
                            onCheckSpecificLabel(BabyBlue.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "SCI-FY",
                        color = RedPink,
                        checked = labelsSelected.contains(RedPink.toArgb()),
                        onCheck = {
                            onCheckSpecificLabel(RedPink.toArgb())
                        }
                    )
                    DefaultCheckBox(
                        text = "HORROR",
                        color = Ruby,
                        checked = labelsSelected.contains(Ruby.toArgb()),
                        onCheck = {
                            onCheckSpecificLabel(Ruby.toArgb())
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}