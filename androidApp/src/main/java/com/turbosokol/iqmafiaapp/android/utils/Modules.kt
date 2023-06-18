package com.turbosokol.iqmafiaapp.android.utils

import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { ReduxViewModel(get()) }
}