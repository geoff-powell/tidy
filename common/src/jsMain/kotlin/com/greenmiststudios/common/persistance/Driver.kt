package com.greenmiststudios.common.persistance

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class DriverFactory {
  public actual fun createDriver(): SqlDriver {
    return WebWorkerDriver(
      Worker(
        js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""") as String
      )
    )
  }
}