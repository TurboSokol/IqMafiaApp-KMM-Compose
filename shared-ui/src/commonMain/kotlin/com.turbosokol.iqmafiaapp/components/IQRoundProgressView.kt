package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable

fun IQRoundProgressView(
    modifier: Modifier = Modifier,
    animProgress: Float,
    diameter: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.onTertiary,
    strokeWidth: Dp = 3.dp
) {

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }
    Canvas(modifier
        .progressSemantics(animProgress)
        .size(diameter)
    ) {
        val startAngle = 270f
        val sweep = animProgress * 360f
        drawRoundProgress(startAngle, sweep, color, stroke)
    }
}

private fun DrawScope.drawRoundProgress(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    val diameterOffset = stroke.width / 2
    val arc = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arc, arc),
        style = stroke
    )
}

