package com.greenmiststudios.common.di

import com.greenmiststudios.common.persistance.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

public val commonModule: Module = module {
  includes(asyncModule)
  includes(presenterModule)

  single { createDatabase(get()) }
}