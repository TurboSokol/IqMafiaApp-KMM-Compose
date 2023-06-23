package com.turbosokol.iqmafiaapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.turbosokol.iqmafiaapp.common.BottomNavBarView
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: ReduxViewModel by inject<ReduxViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                BottomNavBarView(viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}