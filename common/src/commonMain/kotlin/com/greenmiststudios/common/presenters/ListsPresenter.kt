package com.greenmiststudios.common.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.greenmiststudios.common.components.screens.EditListScreen
import com.greenmiststudios.common.data.TidyList
import com.greenmiststudios.common.data.TidyListItem
import com.greenmiststudios.common.navigation.Navigator
import com.greenmiststudios.common.viewmodels.ListsViewEvent
import com.greenmiststudios.common.viewmodels.ListsViewModel
import com.greenmiststudios.tidy.Database
import com.greenmiststudios.tidy.GetAllListsWithItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

public class ListsPresenter(
  private val navigator: Navigator,
  private val database: Database,
  private val ioContext: CoroutineContext,
) : MoleculePresenter<ListsViewModel, ListsViewEvent> {
  @Composable
  override fun models(events: Flow<ListsViewEvent>): ListsViewModel {
    LaunchedEffect(Unit) {
      events.collect {
        when (it) {
          ListsViewEvent.AddList -> navigator.goTo(EditListScreen(EditListScreen.Config.CreateList))
          is ListsViewEvent.OpenList -> navigator.goTo(EditListScreen(EditListScreen.Config.EditList(it.id)))
        }
      }
    }

    val lists by remember {
      database.tidyListQueries.getAllListsWithItems(3L)
        .asFlow()
        .mapToList(ioContext)
        .map { it.asTidyList() }
    }.collectAsState(emptyList())

    return ListsViewModel(lists)
  }
}

public fun List<GetAllListsWithItems>.asTidyList(): List<TidyList> =
  groupBy { it.id to it.name }
    .map {
      TidyList(
        id = it.key.first,
        name = it.key.second,
        items = it.value.mapNotNull(GetAllListsWithItems::asTidyItem)
      )
    }

private fun GetAllListsWithItems.asTidyItem() =
  if (id_ == null || text == null || completed == null) {
    null
  } else {
    TidyListItem(
      id = id_,
      text = text,
      completed = completed,
      assignees = emptyList(), // TODO
    )
  }
