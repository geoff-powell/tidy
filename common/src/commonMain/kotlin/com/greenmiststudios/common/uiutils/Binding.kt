package com.greenmiststudios.common.uiutils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.greenmiststudios.common.presenters.MoleculePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

public data class Binding<Model, Event>(
  val models: StateFlow<Model>,
  val events: MutableSharedFlow<Event>,
)

@Composable
public fun <Model, Event> MoleculePresenter<Model, Event>.bind(scope: CoroutineScope = rememberCoroutineScope()): Binding<Model, Event> {
  val events = remember {
    MutableSharedFlow<Event>()
  }
  return scope.launchMolecule(RecompositionMode.ContextClock) {
    this.models(events)
  }.run {
    Binding(events = events, models = this@run)
  }
}
