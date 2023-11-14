package com.turbosokol.iqmafiaapp.data.character_card

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class CharacterCardModel(
    val type: CharacterCardType
)

enum class CharacterCardType() {
    RED, SHERIFF, BLACK, DON
}
