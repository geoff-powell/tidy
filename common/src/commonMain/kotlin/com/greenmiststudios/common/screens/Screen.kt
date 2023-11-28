package com.greenmiststudios.common.screens

import cafe.adriel.voyager.core.screen.Screen

public interface Screen<Params>: Screen {
  public val params: Params
}