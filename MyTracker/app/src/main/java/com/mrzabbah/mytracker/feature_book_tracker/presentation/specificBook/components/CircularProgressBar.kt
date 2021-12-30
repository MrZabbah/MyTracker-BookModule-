package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrzabbah.mytracker.ui.theme.CasualBlue
import com.mrzabbah.mytracker.ui.theme.LightGray

@Composable
fun CircularProgressBar(
    percentage: Float,
    fontSize: TextUnit = 20.sp,
    radius: Dp = 32.dp,
    color: Color = CasualBlue,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 2000,
    animDelay: Int = 0
) {

    val currPercentage = animateFloatAsState(
        targetValue = percentage,

        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = color,
                -90f,
                360 * currPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currPercentage.value * 100).toInt().toString() + "%",
            color = LightGray,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}