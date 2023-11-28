package com.greenmiststudios.common.di

import com.greenmiststudios.common.di.qualifiers.Threading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

public val asyncModule: Module = module {
  includes(platformAsyncModule)

  single { CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() } }
  single<CoroutineContext>(Threading.MAIN) { Dispatchers.Main + get<CoroutineExceptionHandler>() }
}

public expect val platformAsyncModule: Module