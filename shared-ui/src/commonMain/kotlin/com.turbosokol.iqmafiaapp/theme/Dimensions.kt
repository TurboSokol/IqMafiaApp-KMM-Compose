package com.turbosokol.iqmafiaapp.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

object Dimensions {
    object Padding {
        val micro = 2.dp
        val xsmall = 4.dp
        val small = 8.dp
        val smedium = 10.dp
        val medium = 16.dp
        val large = 32.dp
    }

    object TextSize {
        val xsmall = 8.sp
        val ssmall = 12.sp
        val small = 14.sp
        val smedium = 18.sp
        val medium = 25.sp
        val large = 32.sp
        val huge = 70.sp
        val xhuge = 220.sp
    }

    object Elevation {
        val small = 4.dp
        val medium = 8.dp
        val large = 16.dp
        val xlarge = 24.dp
    }

    object CornerRadius {
        val small = 4.dp
        val medium = 8.dp
        val large = 12.dp
        val xlarge = 24.dp
    }

    object Components {
        object NavBar {
            val barHeight = 60.dp
            val itemHorizontalPadding = 1.dp
            val combinedItemTextBaseline = 12.dp
            val labelFontSize = 16.sp
        }

        object PrimaryButton {
            val elevation = 2.dp
        }

        object IQScoreRow {
            val rowHeight = 70.dp
        }

        object TextField {
            val height = 55.dp
        }
    }

}