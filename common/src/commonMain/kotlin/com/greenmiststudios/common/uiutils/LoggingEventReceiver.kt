package com.greenmiststudios.common.uiutils

internal fun <T> LoggingEventReceiver(): (T) -> Unit = {
  println("PreviewLoggingEventReceiver: $it")
}