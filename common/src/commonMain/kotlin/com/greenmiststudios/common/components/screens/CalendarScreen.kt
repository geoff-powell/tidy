package com.greenmiststudios.common.components.screens

import androidx.compose.runtime.Composable
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.presenters.HomePresenter
import com.greenmiststudios.common.screens.Screen
import com.greenmiststudios.common.screens.bindPresenter
import com.greenmiststudios.common.viewmodels.HomeViewEvent
import com.greenmiststudios.common.viewmodels.HomeViewModel

public object CalendarScreen : Screen<Unit> {
  override val params: Unit = Unit

  @Composable
  override fun Content() {
    CalendarScreen(Unit, {})
  }
}

@Composable
public fun CalendarScreen(viewModel: Unit, onEvent: (Unit) -> Unit) {
  TopAppBarWithContent {
    //TODO
  }
}