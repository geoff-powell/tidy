package com.greenmiststudios.common.components.screens

import androidx.compose.runtime.Composable
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.screens.Screen

public object AgendaScreen : Screen<Unit> {
  override val params: Unit = Unit

  @Composable
  override fun Content() {
    AgendaScreen(Unit, {})
  }
}

@Suppress("UNUSED_PARAMETER")
@Composable
public fun AgendaScreen(viewModel: Unit, onEvent: (Unit) -> Unit) {
  TopAppBarWithContent {
    //TODO
  }
}