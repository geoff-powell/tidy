package com.greenmiststudios.common.persistance

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.greenmiststudios.tidy.Database
import java.util.Properties

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class DriverFactory {
  public actual fun createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver(
      JdbcSqliteDriver.IN_MEMORY,
      properties = Properties().apply { put("foreign_keys", "true") }
    )
    Database.Schema.create(driver)
    return driver
  }
}