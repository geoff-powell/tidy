package com.greenmiststudios.common.providers

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.greenmiststudios.common.resources.StringManager

public val LocalStringManager: ProvidableCompositionLocal<StringManager> = compositionLocalOf<StringManager> { error("No StringManager found!") }