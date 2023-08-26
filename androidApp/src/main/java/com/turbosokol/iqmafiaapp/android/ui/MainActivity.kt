package com.turbosokol.iqmafiaapp.android.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.turbosokol.iqmafiaapp.common.BottomNavBarView
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: ReduxViewModel by inject<ReduxViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val view = LocalView.current
            val window = (view.context as Activity).window
            window.statusBarColor = Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true

            BottomNavBarView(viewModel = viewModel)
        }
    }
}


