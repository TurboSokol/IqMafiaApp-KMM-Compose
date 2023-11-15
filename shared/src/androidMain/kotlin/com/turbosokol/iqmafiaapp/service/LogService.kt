package com.turbosokol.iqmafiaapp.service

import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

@ExperimentalTime
actual class LogServiceImpl actual constructor() : LogService, LogServiceBase() {
 actual override fun logError(message: String) {
  println(Clock.System.now().toString() + ": ERROR: $message")
 }

 actual override fun logWarning(message: String) {
  println(Clock.System.now().toString() + ": WARNING: $message")
 }

 actual override fun logTrace(message: String) {
//  if (PlatformService().isDebugBinary) {
//   println(Clock.System.now().toString() + ": TRACE: $message")
//   store.dispatch(LogAction.LogToTrace(message))
//  }
 }
}