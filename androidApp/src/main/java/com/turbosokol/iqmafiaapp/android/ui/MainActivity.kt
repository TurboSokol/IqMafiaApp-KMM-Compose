package com.turbosokol.iqmafiaapp.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.turbosokol.iqmafiaapp.common.BottomNavBarView
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: ReduxViewModel by inject<ReduxViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            Navigation()
            BottomNavBarView(viewModel = viewModel)
        }
    }
}

//    @Composable
//    fun Navigation() {
//        val navController = rememberNavController()
//        NavHost(navController = navController,
//            startDestination = "splash_screen") {
//            composable("splash_screen") {
//                IQSplashScreen(navController = navController)
//            }
//
//            // Main Screen
//            composable("main_screen") {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text(text = "Main Screen", color = Color.Black, fontSize = 24.sp)
//                }
//            }
//        }
//    }
//}