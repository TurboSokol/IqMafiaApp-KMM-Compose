package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

@Composable
fun IQDayVoteCard(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    voteCandidates: List<Int>,
    voteResult: Map<Int, Int>,
    onVoteClick: (nominantSlot: Int, voteDialogVisible: Boolean) -> Unit
) {
    Surface(
        modifier = modifier
//                .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Start
        ) {
            voteCandidates.forEach { voteNominant ->
                val votingState = remember { mutableStateOf(VotingState.INIT) }
                Card(modifier = Modifier.border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline))
                ) {
                    Column(
                        modifier = if (voteCandidates.size < 6) Modifier else Modifier.weight(1f)
                    ) {
                        //SLOT
                        TextButton(modifier = Modifier.background(
                            color = when (votingState.value) {
                                VotingState.INIT -> MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.6f
                                )

                                VotingState.VOTE_IN_PROGRESS -> MaterialTheme.colorScheme.tertiary
                                VotingState.VOTE_FINISHED -> MaterialTheme.colorScheme.inversePrimary.copy(
                                    alpha = 0.4f
                                )
                            }
                        ),
                            onClick = {
                                MainScope().launch {
                                    votingState.value = VotingState.VOTE_IN_PROGRESS
                                    delay(1500)
                                    votingState.value = VotingState.VOTE_FINISHED
                                    onVoteClick(voteNominant, true)
                                }
                            }) {
                            Text(
                                text = voteNominant.toString(),
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }


                        TextButton(modifier = Modifier
                            .background(Color.Transparent),
                            onClick = {
                                MainScope().launch {
                                    votingState.value = VotingState.VOTE_IN_PROGRESS
                                    delay(1500)
                                    votingState.value = VotingState.VOTE_FINISHED
                                    onVoteClick(voteNominant, true)
                                }
                            }) {
                            val countVoting = voteResult[voteNominant].toString()
                            Text(
                                text = if (countVoting == "null") "-" else countVoting,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }
        }
    }

}

enum class VotingState {
    INIT, VOTE_IN_PROGRESS, VOTE_FINISHED
}