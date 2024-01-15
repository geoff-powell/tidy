package com.greenmiststudios.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.greenmiststudios.common.navigation.VoyagerNavigator
import com.greenmiststudios.common.presenters.MoleculePresenter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
public inline fun <reified PresenterType: MoleculePresenter<Model, Event>, Model, Event> bindPresenter(screen: Screen<*>? = null, navigator: Navigator = LocalNavigator.current!!): Binding<Model, Event> {
  val events = remember { Channel<Event>(Channel.UNLIMITED) }
  val presenter = koinInject<PresenterType> { parametersOf(screen, VoyagerNavigator(navigator)) }
  val viewModel = presenter.models(events.consumeAsFlow())

  DisposableEffect(Unit) {
    onDispose {
      println("Disposing presenter for $screen")
    }
  }
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
  val onEvent: (Event) -> Unit = {
	  println("On event $it")
	  channel.trySend(it)
  }
}