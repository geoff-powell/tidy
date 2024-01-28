package com.greenmiststudios.common.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.presenters.HomePresenter
import com.greenmiststudios.common.screens.Screen
import com.greenmiststudios.common.screens.bindPresenter
import com.greenmiststudios.common.viewmodels.HomeViewEvent
import com.greenmiststudios.common.viewmodels.HomeViewModel

public object HomeScreen : Screen<Unit> {
  override val params: Unit = Unit

  @Composable
  override fun Content() {
    val binding = bindPresenter<HomePresenter, HomeViewModel, HomeViewEvent>()
    HomeScreen(viewModel = binding.viewModel, onEvent = binding.onEvent)
  }
}

@Suppress("UNUSED_PARAMETER")
@Composable
private fun HomeScreen(viewModel: HomeViewModel, onEvent: (HomeViewEvent) -> Unit) {
  TopAppBarWithContent {
    Box(modifier = Modifier.fillMaxWidth()) {
      Text("Home")
    }

  }
}