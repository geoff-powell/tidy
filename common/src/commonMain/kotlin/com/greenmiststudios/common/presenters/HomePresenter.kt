package com.greenmiststudios.common.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.greenmiststudios.common.viewmodels.HomeViewEvent
import com.greenmiststudios.common.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.Flow

public class HomePresenter : MoleculePresenter<HomeViewModel, HomeViewEvent> {

  @Suppress("UNUSED_ANONYMOUS_PARAMETER")
  @Composable
  override fun models(events: Flow<HomeViewEvent>): HomeViewModel {
    LaunchedEffect(Unit) {
      events.collect { event ->

      }
    }

    return HomeViewModel
  }
}