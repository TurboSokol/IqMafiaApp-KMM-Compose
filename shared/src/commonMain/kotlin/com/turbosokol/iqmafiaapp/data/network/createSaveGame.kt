package com.turbosokol.iqmafiaapp.data.network

fun createSaveGame(): saveGame {
    return saveGame(
        id = "game-123",
        players = listOf(
            Player(
                id = "player-1",
                name = "Player 1",
                character = Character(
                    type = CharacterType.RED,
                    strength = 10,
                    weakness = listOf(
                        Weakness(
                            type = WeaknessType.GOLD,
                            description = "Gold is a weakness"
                        ),
                        Weakness(
                            type = WeaknessType.SILVER,
                            description = "Silver is a weakness"
                        )
                    )
                )
            ),
            Player(
                id = "player-2",
                name = "Player 2",
                character = Character(
                    type = CharacterType.BLACK,
                    strength = 15,
                    weakness = listOf(
                        Weakness(
                            type = WeaknessType.COPPER,
                            description = "Copper is a weakness"
                        )
                    )
                )
            )
        ),
        currentPlayer = "player-1",
        currentPhase = Phase.DAY,
        board = Board(
            rooms = listOf(
                Room(
                    id = "room-1",
                    name = "Room 1",
                    cards = listOf(
                        Card(
                            id = "card-1",
                            name = "Card 1",
                            description = "Description of Card 1"
                        ),
                        Card(
                            id = "card-2",
                            name = "Card 2",
                            description = "Description of Card 2"
                        )
                    )
                ),
                Room(
                    id = "room-2",
                    name = "Room 2",
                    cards = listOf(
                        Card(
                            id = "card-3",
                            name = "Card 3",
                            description = "Description of Card 3"
                        ),
                        Card(
                            id = "card-4",
                            name = "Card 4",
                            description = "Description of Card 4"
                        )
                    )
                )
            ),
            treasures = listOf(
                Treasure(
                    id = "treasure-1",
                    name = "Treasure 1",
                    description = "Description of Treasure 1"
                ),
                Treasure(
                    id = "treasure-2",
                    name = "Treasure 2",
                    description = "Description of Treasure 2"
                )
            )
        ),
        votes = listOf(
            Vote(
                player = "player-2",
                target = "room-1",
                outcome = Outcome.ACCEPT
            ),
            Vote(
                player = "player-1",
                target = "treasure-2",
                outcome = Outcome.REJECT
            )
        )
    )
}