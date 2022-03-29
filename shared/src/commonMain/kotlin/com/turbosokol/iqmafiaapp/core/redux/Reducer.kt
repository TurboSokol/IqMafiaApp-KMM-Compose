package com.turbosokol.iqmafiaapp.core.redux

interface Reducer<T : GeneralState> {
    fun reduce(oldState: T, action: Action): T
}