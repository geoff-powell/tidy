package com.greenmiststudios.common.navigation

public class VoyagerNavigator(
  private val voyagerNavigator: cafe.adriel.voyager.navigator.Navigator
) : Navigator {
    override fun goTo(screen: NavScreen) {
      when(screen) {
        Back -> voyagerNavigator.pop()
        is VoyagerScreen -> voyagerNavigator.push(screen)
      }
    }
}