package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.turbosokol.iqmafiaapp.theme.Colors

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun CollapsedSwitchFAB(
    modifier: Modifier,
    collapsedText: String,
    activeCollapsedText: String,
    expandedText: String,
    isToogled: Boolean,
    onToogleClick: () -> Unit
) {
    var mToogled by remember { mutableStateOf(isToogled) }
    var isCollapsed by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isCollapsed,
        exit = slideOutHor(),
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = { isCollapsed = false },
            modifier = modifier,
            content = { Text( if(mToogled) activeCollapsedText else collapsedText) },
            backgroundColor = if (mToogled) Colors.secondary else Colors.primary
        )
    }

    AnimatedVisibility(
        visible = !isCollapsed,
        exit = slideOutHor(),
        modifier = modifier
    ) {
        ExtendedFloatingActionButton(
            onClick = { isCollapsed = true },
            modifier = modifier,
            text = { Text(expandedText) },
            icon = {
                Switch(checked = mToogled, onCheckedChange = {
                    onToogleClick()
                    mToogled = !mToogled
                })
            },
            backgroundColor =  if (mToogled) Colors.secondary else Colors.primary
        )
    }

}

fun slideOutHor(
    animationSpec: FiniteAnimationSpec<IntOffset> =
        spring(
            stiffness = Spring.DampingRatioHighBouncy,
            visibilityThreshold = IntOffset(0,0)
        ),
    targetOffsetX: (fullWidth: Int) -> Int = { it },
): ExitTransition =
    slideOut(
        targetOffset = { IntOffset(targetOffsetX(it.width),0) },
        animationSpec = animationSpec
    )

fun slideInHor(
    animationSpec: FiniteAnimationSpec<IntOffset> =
        spring(
            stiffness = Spring.DampingRatioHighBouncy,
            visibilityThreshold = IntOffset.VisibilityThreshold
        ),
    initialOffsetX: (fullWidth: Int) -> Int = { it },
): EnterTransition =
    slideIn(
        initialOffset = { IntOffset(initialOffsetX(it.width), 0) },
        animationSpec = animationSpec
    )
