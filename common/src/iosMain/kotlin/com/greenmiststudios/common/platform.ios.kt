package com.greenmiststudios.common

import androidx.compose.runtime.Composable
import com.greenmiststudios.common.components.App

public actual fun getPlatformName(): String {
  return "iOS"
}

@Composable
public fun UIShow() {
  App()
}