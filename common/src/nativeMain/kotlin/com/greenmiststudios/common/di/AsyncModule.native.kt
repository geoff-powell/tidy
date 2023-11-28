package com.greenmiststudios.common.di

import com.greenmiststudios.common.di.qualifiers.Threading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

public actual val platformAsyncModule: Module = module {
  single<CoroutineContext>(Threading.IO) { Dispatchers.IO + get<CoroutineExceptionHandler>() }
}