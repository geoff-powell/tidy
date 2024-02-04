package com.greenmiststudios.common.uiutils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.greenmiststudios.common.di.startTidyKoin
import com.greenmiststudios.common.providers.LocalStringManager
import com.greenmiststudios.common.resources.RealStringManager
import org.koin.mp.KoinPlatformTools

@Composable
public fun TidyPreview(content: @Composable () -> Unit) {
  if (KoinPlatformTools.defaultContext().getOrNull() == null) {
    startTidyKoin()
  }

  CompositionLocalProvider(
    // TODO: Move to dependency injection
    LocalStringManager provides RealStringManager(),
  ) {
    content()
  }
}