package com.greenmiststudios.common.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.components.screens.HomeScreen
import com.greenmiststudios.common.components.screens.ListsScreen
import com.greenmiststudios.common.presenters.HomePresenter
import com.greenmiststudios.common.presenters.ListsPresenter
import com.greenmiststudios.common.presenters.MoleculePresenter
import com.greenmiststudios.common.providers.LocalStringManager
import com.greenmiststudios.common.resources.StringKey
import com.greenmiststudios.common.viewmodels.HomeViewEvent
import com.greenmiststudios.common.viewmodels.HomeViewModel
import com.greenmiststudios.common.viewmodels.ListsViewEvent
import com.greenmiststudios.common.viewmodels.ListsViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.compose.koinInject
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

public sealed interface Tab : Tab {
  public data object Home : com.greenmiststudios.common.screens.Tab {
    @Composable
    override fun Content() {
      val binding = bindPresenter<HomePresenter, HomeViewModel, HomeViewEvent>()
      HomeScreen(viewModel = binding.viewModel, onEvent = binding.onEvent)
    }

    override val options: TabOptions
      @Composable
      get() = TabOptions(
        index = 0u,
        icon = rememberVectorPainter(Icons.Outlined.Home),
        title = LocalStringManager.current[StringKey.TAB_HOME],
      )
  }

  public data object Lists : com.greenmiststudios.common.screens.Tab {
    @Composable
    override fun Content() {
      val binding = bindPresenter<ListsPresenter, ListsViewModel, ListsViewEvent>()
      ListsScreen(listsViewModel = binding.viewModel, onEvent = binding.onEvent)
    }

    override val options: TabOptions
      @Composable
      get() = TabOptions(
        index = 1u,
        icon = rememberVectorPainter(Icons.Outlined.Assignment),
        title = LocalStringManager.current[StringKey.TAB_LISTS],
      )
  }

  public data object Calendar : com.greenmiststudios.common.screens.Tab {
    @Composable
    override fun Content() {
      TopAppBarWithContent {
        // TODO
      }
    }

    override val options: TabOptions
      @Composable
      get() = TabOptions(
        index = 2u,
        icon = rememberVectorPainter(Icons.Outlined.CalendarMonth),
        title = LocalStringManager.current[StringKey.TAB_CALENDAR],
      )
  }
}

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