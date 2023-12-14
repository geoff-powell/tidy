package com.greenmiststudios.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.greenmiststudios.common.presenters.MoleculePresenter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
public inline fun <reified PresenterType: MoleculePresenter<Model, Event>, Model, Event> bindPresenter(screen: Screen<*>? = null): Binding<Model, Event> {
  val events = remember { Channel<Event>(Channel.UNLIMITED) }
  val presenter = koinInject<PresenterType> { parametersOf(screen) }
  val viewModel = presenter.models(events.consumeAsFlow())
  return Binding(viewModel, events)
}

@Composable
public inline fun <reified PresenterType: MoleculePresenter<Model, Event>, Model, Event> Screen<*>.bindPresenter(): Binding<Model, Event>
  = bindPresenter<PresenterType, Model, Event>(this)

@Stable
public data class Binding<ViewModel, Event> (
  val viewModel: ViewModel,
  val channel: SendChannel<Event>,
) {
  val onEvent: (Event) -> Unit = { channel.trySend(it) }
}