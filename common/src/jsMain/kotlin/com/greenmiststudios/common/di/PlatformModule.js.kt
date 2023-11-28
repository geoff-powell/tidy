package com.greenmiststudios.common.di

import com.greenmiststudios.common.di.qualifiers.Threading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

public actual val platformModule: Module = module {
  single(Threading.IO) { Dispatchers.Default + get<CoroutineExceptionHandler>() }
}