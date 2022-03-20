package com.turbosokol.iqmafiaapp.judge.player.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.judge.player.model.Player

class PlayerReducer : Reducer<PlayersState> {
    override fun reduce(oldState: PlayersState, action: Action): PlayersState {
        return when (action) {
            is PlayerAction.InitPlayers -> {
                PlayersState.getDefaultState()
            }

            is PlayerAction.EditPlayerName -> {
                when (action.slot) {
                    1 -> {
                        oldState.copy(
                            player1 = Player(
                                slot = oldState.player1.slot,
                                name = action.name,
                                falls = oldState.player1.falls,
                                bestMove = oldState.player1.bestMove,
                                score = oldState.player1.score
                            )
                        )
                    }
                    2 -> {
                        oldState.copy(
                            player2 = Player(
                                slot = oldState.player2.slot,
                                name = action.name,
                                falls = oldState.player2.falls,
                                bestMove = oldState.player2.bestMove,
                                score = oldState.player2.score
                            )
                        )
                    }
                    3 -> {
                        oldState.copy(
                            player3 = Player(
                                slot = oldState.player3.slot,
                                name = action.name,
                                falls = oldState.player3.falls,
                                bestMove = oldState.player3.bestMove,
                                score = oldState.player3.score
                            )
                        )
                    }
                    4 -> {
                        oldState.copy(
                            player4 = Player(
                                slot = oldState.player4.slot,
                                name = action.name,
                                falls = oldState.player4.falls,
                                bestMove = oldState.player4.bestMove,
                                score = oldState.player4.score
                            )
                        )
                    }
                    5 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player5.slot,
                                name = action.name,
                                falls = oldState.player5.falls,
                                bestMove = oldState.player5.bestMove,
                                score = oldState.player5.score
                            )
                        )
                    }
                    6 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player6.slot,
                                name = action.name,
                                falls = oldState.player6.falls,
                                bestMove = oldState.player6.bestMove,
                                score = oldState.player6.score
                            )
                        )
                    }
                    7 -> {
                        oldState.copy(
                            player6 = Player(
                                slot = oldState.player7.slot,
                                name = action.name,
                                falls = oldState.player7.falls,
                                bestMove = oldState.player7.bestMove,
                                score = oldState.player7.score
                            )
                        )
                    }
                    8 -> {
                        oldState.copy(
                            player8 = Player(
                                slot = oldState.player8.slot,
                                name = action.name,
                                falls = oldState.player8.falls,
                                bestMove = oldState.player8.bestMove,
                                score = oldState.player8.score
                            )
                        )
                    }
                    9 -> {
                        oldState.copy(
                            player9 = Player(
                                slot = oldState.player9.slot,
                                name = action.name,
                                falls = oldState.player9.falls,
                                bestMove = oldState.player9.bestMove,
                                score = oldState.player9.score
                            )
                        )
                    }
                    10 -> {
                        oldState.copy(
                            player10 = Player(
                                slot = oldState.player10.slot,
                                name = action.name,
                                falls = oldState.player10.falls,
                                bestMove = oldState.player10.bestMove,
                                score = oldState.player10.score
                            )
                        )
                    }
                    else -> oldState
                }
            }

            is PlayerAction.EditPlayerFalls -> {
                when (action.slot) {
                    1 -> {
                        oldState.copy(
                            player1 = Player(
                                slot = oldState.player1.slot,
                                name = oldState.player1.name,
                                falls = action.falls,
                                bestMove = oldState.player1.bestMove,
                                score = oldState.player1.score
                            )
                        )
                    }
                    2 -> {
                        oldState.copy(
                            player2 = Player(
                                slot = oldState.player2.slot,
                                name = oldState.player2.name,
                                falls = action.falls,
                                bestMove = oldState.player2.bestMove,
                                score = oldState.player2.score
                            )
                        )
                    }
                    3 -> {
                        oldState.copy(
                            player3 = Player(
                                slot = oldState.player3.slot,
                                name = oldState.player3.name,
                                falls = action.falls,
                                bestMove = oldState.player3.bestMove,
                                score = oldState.player3.score
                            )
                        )
                    }
                    4 -> {
                        oldState.copy(
                            player4 = Player(
                                slot = oldState.player4.slot,
                                name = oldState.player4.name,
                                falls = action.falls,
                                bestMove = oldState.player4.bestMove,
                                score = oldState.player4.score
                            )
                        )
                    }
                    5 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player5.slot,
                                name = oldState.player5.name,
                                falls = action.falls,
                                bestMove = oldState.player5.bestMove,
                                score = oldState.player5.score
                            )
                        )
                    }
                    6 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player6.slot,
                                name = oldState.player6.name,
                                falls = action.falls,
                                bestMove = oldState.player6.bestMove,
                                score = oldState.player6.score
                            )
                        )
                    }
                    7 -> {
                        oldState.copy(
                            player6 = Player(
                                slot = oldState.player7.slot,
                                name = oldState.player7.name,
                                falls = action.falls,
                                bestMove = oldState.player7.bestMove,
                                score = oldState.player7.score
                            )
                        )
                    }
                    8 -> {
                        oldState.copy(
                            player8 = Player(
                                slot = oldState.player8.slot,
                                name = oldState.player8.name,
                                falls = action.falls,
                                bestMove = oldState.player8.bestMove,
                                score = oldState.player8.score
                            )
                        )
                    }
                    9 -> {
                        oldState.copy(
                            player9 = Player(
                                slot = oldState.player9.slot,
                                name = oldState.player9.name,
                                falls = action.falls,
                                bestMove = oldState.player9.bestMove,
                                score = oldState.player9.score
                            )
                        )
                    }
                    10 -> {
                        oldState.copy(
                            player10 = Player(
                                slot = oldState.player10.slot,
                                name = oldState.player10.name,
                                falls = action.falls,
                                bestMove = oldState.player10.bestMove,
                                score = oldState.player10.score
                            )
                        )
                    }
                    else -> oldState
                }
            }

            is PlayerAction.EditBestMove -> {
                when (action.slot) {
                    1 -> {
                        oldState.copy(
                            player1 = Player(
                                slot = oldState.player1.slot,
                                name = oldState.player1.name,
                                falls = oldState.player1.falls,
                                bestMove = action.bestMove,
                                score = oldState.player1.score
                            )
                        )
                    }
                    2 -> {
                        oldState.copy(
                            player2 = Player(
                                slot = oldState.player2.slot,
                                name = oldState.player2.name,
                                falls = oldState.player2.falls,
                                bestMove = action.bestMove,
                                score = oldState.player2.score
                            )
                        )
                    }
                    3 -> {
                        oldState.copy(
                            player3 = Player(
                                slot = oldState.player3.slot,
                                name = oldState.player3.name,
                                falls = oldState.player3.falls,
                                bestMove = action.bestMove,
                                score = oldState.player3.score
                            )
                        )
                    }
                    4 -> {
                        oldState.copy(
                            player4 = Player(
                                slot = oldState.player4.slot,
                                name = oldState.player4.name,
                                falls = oldState.player4.falls,
                                bestMove = action.bestMove,
                                score = oldState.player4.score
                            )
                        )
                    }
                    5 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player5.slot,
                                name = oldState.player5.name,
                                falls = oldState.player5.falls,
                                bestMove = action.bestMove,
                                score = oldState.player5.score
                            )
                        )
                    }
                    6 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player6.slot,
                                name = oldState.player6.name,
                                falls = oldState.player6.falls,
                                bestMove = action.bestMove,
                                score = oldState.player6.score
                            )
                        )
                    }
                    7 -> {
                        oldState.copy(
                            player6 = Player(
                                slot = oldState.player7.slot,
                                name = oldState.player7.name,
                                falls = oldState.player7.falls,
                                bestMove = action.bestMove,
                                score = oldState.player7.score
                            )
                        )
                    }
                    8 -> {
                        oldState.copy(
                            player8 = Player(
                                slot = oldState.player8.slot,
                                name = oldState.player8.name,
                                falls = oldState.player8.falls,
                                bestMove = action.bestMove,
                                score = oldState.player8.score
                            )
                        )
                    }
                    9 -> {
                        oldState.copy(
                            player9 = Player(
                                slot = oldState.player9.slot,
                                name = oldState.player9.name,
                                falls = oldState.player9.falls,
                                bestMove = action.bestMove,
                                score = oldState.player9.score
                            )
                        )
                    }
                    10 -> {
                        oldState.copy(
                            player10 = Player(
                                slot = oldState.player10.slot,
                                name = oldState.player10.name,
                                falls = oldState.player10.falls,
                                bestMove = action.bestMove,
                                score = oldState.player10.score
                            )
                        )
                    }
                    else -> oldState
                }
            }

            is PlayerAction.EditScore -> {
                when (action.slot) {
                    1 -> {
                        oldState.copy(
                            player1 = Player(
                                slot = oldState.player1.slot,
                                name = oldState.player1.name,
                                falls = oldState.player1.falls,
                                bestMove = oldState.player1.bestMove,
                                score = action.score
                            )
                        )
                    }
                    2 -> {
                        oldState.copy(
                            player2 = Player(
                                slot = oldState.player2.slot,
                                name = oldState.player2.name,
                                falls = oldState.player2.falls,
                                bestMove = oldState.player2.bestMove,
                                score = action.score
                            )
                        )
                    }
                    3 -> {
                        oldState.copy(
                            player3 = Player(
                                slot = oldState.player3.slot,
                                name = oldState.player3.name,
                                falls = oldState.player3.falls,
                                bestMove = oldState.player3.bestMove,
                                score = action.score
                            )
                        )
                    }
                    4 -> {
                        oldState.copy(
                            player4 = Player(
                                slot = oldState.player4.slot,
                                name = oldState.player4.name,
                                falls = oldState.player4.falls,
                                bestMove = oldState.player4.bestMove,
                                score = action.score
                            )
                        )
                    }
                    5 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player5.slot,
                                name = oldState.player5.name,
                                falls = oldState.player5.falls,
                                bestMove = oldState.player5.bestMove,
                                score = action.score
                            )
                        )
                    }
                    6 -> {
                        oldState.copy(
                            player5 = Player(
                                slot = oldState.player6.slot,
                                name = oldState.player6.name,
                                falls = oldState.player6.falls,
                                bestMove = oldState.player6.bestMove,
                                score = action.score
                            )
                        )
                    }
                    7 -> {
                        oldState.copy(
                            player6 = Player(
                                slot = oldState.player7.slot,
                                name = oldState.player7.name,
                                falls = oldState.player7.falls,
                                bestMove = oldState.player7.bestMove,
                                score = action.score
                            )
                        )
                    }
                    8 -> {
                        oldState.copy(
                            player8 = Player(
                                slot = oldState.player8.slot,
                                name = oldState.player8.name,
                                falls = oldState.player8.falls,
                                bestMove = oldState.player8.bestMove,
                                score = action.score
                            )
                        )
                    }
                    9 -> {
                        oldState.copy(
                            player9 = Player(
                                slot = oldState.player9.slot,
                                name = oldState.player9.name,
                                falls = oldState.player9.falls,
                                bestMove = oldState.player9.bestMove,
                                score = action.score
                            )
                        )
                    }
                    10 -> {
                        oldState.copy(
                            player10 = Player(
                                slot = oldState.player10.slot,
                                name = oldState.player10.name,
                                falls = oldState.player10.falls,
                                bestMove = oldState.player10.bestMove,
                                score = action.score
                            )
                        )
                    }
                    else -> oldState
                }
            }
            else -> oldState
        }
    }
}