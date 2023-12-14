package com.greenmiststudios.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.greenmiststudios.common.components.App
import platform.UIKit.UIViewController

public actual fun getPlatformName(): String {
  return "iOS"
}

@Composable
public fun UIShow() {
  App()
}