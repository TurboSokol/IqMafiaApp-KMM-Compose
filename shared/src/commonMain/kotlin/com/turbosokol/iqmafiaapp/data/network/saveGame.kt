package com.turbosokol.iqmafiaapp.data.network

data class saveGame(
    val id: String, // Unique ID for the game
    val players: List<Player>, // List of players in the game
    val currentPlayer: String, // ID of the current player
    val currentPhase: Phase, // Current phase of the game
    val board: Board, // Current state of the board
    val votes: List<Vote> // List of votes in the game
)

data class Player(
    val id: String, // Unique ID for the player
    val name: String, // Name of the player
    val character: Character // Character card for the player
)

enum class Phase {
    SETUP, // Game setup phase
    DAY, // Day phase
    NIGHT, // Night phase
    REVEAL, // Reveal phase
    RESULT // Result phase
}

data class Board(
    val rooms: List<Room>, // List of rooms on the board
    val treasures: List<Treasure> // List of treasures on the board
)

data class Room(
    val id: String, // Unique ID for the room
    val name: String, // Name of the room
    val cards: List<Card> // List of cards in the room
)

data class Card(
    val id: String, // Unique ID for the card
    val name: String, // Name of the card
    val description: String // Description of the card
)

data class Treasure(
    val id: String, // Unique ID for the treasure
    val name: String, // Name of the treasure
    val description: String // Description of the treasure
)

data class Vote(
    val player: String, // ID of the player who cast the vote
    val target: String, // ID of the target of the vote
    val outcome: Outcome // Outcome of the vote
)

enum class Outcome {
    ACCEPT, // Accept the target
    REJECT // Reject the target
}

data class Character(
    val type: CharacterType, // Type of character (e.g. Sheriff, Don, etc.)
    val strength: Int, // Strength of the character
    val weakness: List<Weakness> // List of weaknesses for the character
)

enum class CharacterType {
    RED, // Regular red player
    BLACK, // Black Widow
    DON, // Don of the Underworld
    SHERIFF // Sheriff of Nottingham
}

data class Weakness(
    val type: WeaknessType, // Type of weakness (e.g. gold, silver, etc.)
    val description: String // Description of the weakness
)

enum class WeaknessType {
    GOLD, // Gold weakness
    SILVER, // Silver weakness
    COPPER // Copper weakness
}