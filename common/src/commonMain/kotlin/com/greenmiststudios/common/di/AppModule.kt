package com.greenmiststudios.common.di

import org.koin.core.module.Module

public fun appModule(): List<Module> = listOf(commonModule, platformModule)