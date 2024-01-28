package com.greenmiststudios.common.presenters

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

public interface MoleculePresenter<Model, Event>: Presenter<Model, Event> {
  @Composable
  public fun models(events: Flow<Event>): Model
}

public interface Presenter<Model, Event>