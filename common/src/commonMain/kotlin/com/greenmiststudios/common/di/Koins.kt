package com.greenmiststudios.common.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin as startInternalKoin

public fun startTidyKoin(platformSpecificConfig: KoinAppDeclaration = {}) {
  startInternalKoin {
    modules(appModule())
    platformSpecificConfig()
  }
}