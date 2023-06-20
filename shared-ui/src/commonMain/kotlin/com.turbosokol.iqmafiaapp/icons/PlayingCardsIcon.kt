package com.turbosokol.iqmafiaapp.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.Outlined.PlayingCardsIcon: ImageVector
    get() {
        if (_playingCardsIcon != null) {
            return _playingCardsIcon!!
        }
        _playingCardsIcon = Builder(name = "PlayingCardsIcon",
                defaultWidth = 28.0.dp, defaultHeight = 28.0.dp, viewportWidth = 960.0f,
                viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(604.0f, 571.0f)
                lineToRelative(40.0f, -145.0f)
                lineToRelative(-124.0f, -85.0f)
                lineToRelative(-40.0f, 145.0f)
                lineToRelative(124.0f, 85.0f)
                close()
                moveTo(195.0f, 800.0f)
                lineToRelative(-66.0f, -27.0f)
                quadToRelative(-32.17f, -14.026f, -43.585f, -48.013f)
                quadTo(74.0f, 691.0f, 92.0f, 658.0f)
                lineToRelative(103.0f, -245.0f)
                verticalLineToRelative(387.0f)
                close()
                moveTo(337.0f, 858.0f)
                quadToRelative(-33.825f, 0.0f, -57.913f, -24.0f)
                quadTo(255.0f, 810.0f, 255.0f, 776.0f)
                verticalLineToRelative(-313.0f)
                lineToRelative(137.0f, 368.0f)
                quadToRelative(3.0f, 8.0f, 6.5f, 14.0f)
                reflectiveQuadToRelative(9.5f, 13.0f)
                horizontalLineToRelative(-71.0f)
                close()
                moveTo(520.0f, 833.0f)
                quadToRelative(-23.0f, 8.0f, -45.932f, -2.203f)
                quadTo(451.137f, 820.593f, 443.0f, 798.0f)
                lineTo(251.0f, 276.0f)
                quadToRelative(-8.0f, -23.0f, 2.441f, -45.875f)
                quadTo(263.881f, 207.25f, 287.0f, 199.0f)
                lineToRelative(319.0f, -115.0f)
                quadToRelative(23.0f, -8.0f, 46.5f, 2.0f)
                reflectiveQuadToRelative(31.5f, 33.0f)
                lineToRelative(193.0f, 521.0f)
                quadToRelative(8.0f, 23.0f, -2.203f, 45.875f)
                quadTo(864.593f, 708.75f, 842.0f, 717.0f)
                lineTo(520.0f, 833.0f)
                close()
                moveTo(500.0f, 776.0f)
                lineTo(818.0f, 660.0f)
                lineTo(627.0f, 141.0f)
                lineTo(309.0f, 256.0f)
                lineTo(500.0f, 776.0f)
                close()
                moveTo(564.0f, 458.0f)
                close()
            }
        }
        .build()
        return _playingCardsIcon!!
    }

private var _playingCardsIcon: ImageVector? = null
