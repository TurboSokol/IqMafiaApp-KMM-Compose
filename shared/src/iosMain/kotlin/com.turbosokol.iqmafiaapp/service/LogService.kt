package com.turbosokol.iqmafiaapp.service

import platform.Foundation.NSLog
import kotlin.time.ExperimentalTime

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

@ExperimentalTime
actual class LogServiceImpl actual constructor() : LogService, LogServiceBase() {
    actual override fun logError(message: String) {
        NSLog("ERROR: $message")
    }

    actual override fun logWarning(message: String) {
        NSLog("WARNING: $message")
    }

    actual override fun logTrace(message: String) {
//        if (PlatformService().isDebugBinary) {
//            NSLog("TRACE: $message")
//            store.dispatch(LogAction.LogToTrace(message))
//        }
    }
}