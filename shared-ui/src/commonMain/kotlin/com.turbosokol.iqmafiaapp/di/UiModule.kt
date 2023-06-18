package com.turbosokol.iqmafiaapp.di

import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import org.koin.dsl.module

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

val uiModule = module {
    single { ReduxViewModel(store = get()) }
}