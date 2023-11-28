package com.greenmiststudios.common.persistance

import app.cash.sqldelight.db.SqlDriver
import com.greenmiststudios.tidy.Database

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public expect class DriverFactory {
  public fun createDriver(): SqlDriver
}

public fun createDatabase(driverFactory: DriverFactory): Database {
  val driver = driverFactory.createDriver()

  // Do more work with the database (see below).
  return Database(driver)
}