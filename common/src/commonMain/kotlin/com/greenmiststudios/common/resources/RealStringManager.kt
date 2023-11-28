package com.greenmiststudios.common.resources

public class RealStringManager: StringManager {
  public override operator fun get(key: StringKey): String {
    return key.value
  }
}
