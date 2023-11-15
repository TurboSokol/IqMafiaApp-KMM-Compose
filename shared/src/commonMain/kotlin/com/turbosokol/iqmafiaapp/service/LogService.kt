package com.turbosokol.iqmafiaapp.service

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Store
import com.turbosokol.iqmafiaapp.features.app.AppState
import io.ktor.client.plugins.logging.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

interface LogService {
    fun logError(message: String)
    fun logWarning(message: String)
    fun logTrace(message: String)
}

@ExperimentalTime
open class LogServiceBase : KoinComponent {
    protected val store: Store<AppState, Action, Effect> by inject()
}

@ExperimentalTime
expect class LogServiceImpl constructor() : LogService, LogServiceBase {
    override fun logError(message: String)
    override fun logWarning(message: String)
    override fun logTrace(message: String)
}

@ExperimentalTime
class WebClientLogger(private val logService: LogService) : Logger {
    override fun log(message: String) {
        logService.logTrace(message)
    }
}