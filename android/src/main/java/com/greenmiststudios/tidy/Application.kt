package com.greenmiststudios.tidy

import android.app.Application
import com.greenmiststudios.common.di.appModule
import com.greenmiststudios.common.di.startTidyKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TidyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    startTidyKoin {
      // Log Koin into Android logger
      androidLogger()
      // Reference Android context
      androidContext(this@TidyApplication)
    }
  }
}