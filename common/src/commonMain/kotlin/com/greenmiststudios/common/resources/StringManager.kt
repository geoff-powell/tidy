package com.greenmiststudios.common.resources

public interface StringManager {
  public operator fun get(key: StringKey): String
}

public enum class StringKey(public val value: String) {
  APP_NAME("Tidy"),
  TAB_HOME("Home"),
  TAB_AGENDA("Agenda"),
  TAB_TODO("Todo"),
}