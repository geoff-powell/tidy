package com.greenmiststudios.common.persistance

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.greenmiststudios.tidy.Database

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class DriverFactory(private val context: Context) {
  public actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(
      schema = Database.Schema,
      context = context,
      callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
        override fun onOpen(db: SupportSQLiteDatabase) {
          db.setForeignKeyConstraintsEnabled(true)
        }
      },
      name = "tidy.db"
    )
  }
}