package com.turbosokol.iqmafiaapp.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun DayScreenView(viewModel: ReduxViewModel) {
 Text(text = "Day\nList of players\nVoting\nFaults\nVote Nomination")
}