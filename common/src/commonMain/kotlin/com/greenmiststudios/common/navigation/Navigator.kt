package com.greenmiststudios.common.navigation

import cafe.adriel.voyager.core.screen.Screen

public interface Navigator {
  public fun goTo(screen: NavScreen)
}

public interface NavScreen

public interface VoyagerScreen : NavScreen, Screen

public data object Back : NavScreen