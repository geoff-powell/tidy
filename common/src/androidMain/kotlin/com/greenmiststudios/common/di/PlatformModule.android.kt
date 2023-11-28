package com.greenmiststudios.common.di

import com.greenmiststudios.common.persistance.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

public actual val platformModule: Module = module {
  factory { DriverFactory(get()) }
}