package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.round

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun IQCollapsedSwitchFABView(
    modifier: Modifier,
    collapsedText: String,
    activeCollapsedText: String,
    expandedText: String,
    isToogled: Boolean,
    onToogleClick: () -> Unit
) {
    var mToggled by remember { mutableStateOf(isToogled) }
    var isCollapsed by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isCollapsed,
        exit = slideOutHor(),
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = { isCollapsed = false },
            modifier = modifier,
            shape = CircleShape,
            content = { Text( if(mToggled) activeCollapsedText else collapsedText) },
            containerColor = if (mToggled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inversePrimary
        )
    }

    AnimatedVisibility(
        visible = !isCollapsed,
        exit = slideOutHor() + fadeOut(),
        modifier = modifier
    ) {
        ExtendedFloatingActionButton(
            onClick = { isCollapsed = true },
            modifier = modifier,
            text = { Text(expandedText) },
            icon = {
                Switch(checked = mToggled, onCheckedChange = {
                    onToogleClick()
                    mToggled = !mToggled
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1200)
                        isCollapsed = true
                    }
                })
            },
            containerColor =  if (mToggled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inversePrimary
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
