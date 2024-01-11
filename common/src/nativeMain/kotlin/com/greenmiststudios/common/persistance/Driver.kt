package com.greenmiststudios.common.persistance

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.greenmiststudios.tidy.Database

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class DriverFactory {
  public actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(
      schema = Database.Schema,
      onConfiguration = { config: DatabaseConfiguration ->
        config.copy(
          extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
        )
      },
      maxReaderConnections = 4,
      name = "tidy.db"
    )
  }
}