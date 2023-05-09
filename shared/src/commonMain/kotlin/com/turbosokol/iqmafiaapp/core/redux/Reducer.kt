package com.turbosokol.iqmafiaapp.core.redux

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

interface Reducer<G : GeneralState> {
    fun reduce(oldState: G, action: Action): G
}