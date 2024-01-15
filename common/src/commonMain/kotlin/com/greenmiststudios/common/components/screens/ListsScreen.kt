package com.greenmiststudios.common.components.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.greenmiststudios.common.components.ActionButton
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.presenters.ListsPresenter
import com.greenmiststudios.common.screens.Screen
import com.greenmiststudios.common.screens.bindPresenter
import com.greenmiststudios.common.viewmodels.ListsViewEvent
import com.greenmiststudios.common.viewmodels.ListsViewEvent.AddList
import com.greenmiststudios.common.viewmodels.ListsViewModel

public object ListsScreen : Screen<Unit> {
  override val params: Unit = Unit

  @Composable
  override fun Content() {
    val binding = bindPresenter<ListsPresenter, ListsViewModel, ListsViewEvent>()
    ListsScreen(listsViewModel = binding.viewModel, onEvent = binding.onEvent)
  }
}

@Composable
public fun ListsScreen(listsViewModel: ListsViewModel, onEvent: (ListsViewEvent) -> Unit) {
  val navigator = LocalNavigator.current!!

  TopAppBarWithContent(
    modifier = Modifier.fillMaxSize(),
    actionButtons = listOf(
      ActionButton(action = "Add", icon = rememberVectorPainter(Icons.Filled.Add)) {
        onEvent(AddList)
      }
    )
  ) {
    LazyColumn(
      modifier = Modifier,
      verticalArrangement = spacedBy(16.dp),
    ) {
      items(listsViewModel.lists.size, key = { listsViewModel.lists[it].id }) {
        val list = listsViewModel.lists[it]

        Card(
          modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClickLabel = list.name) {
              onEvent(ListsViewEvent.OpenList(list.id))
            },
        ) {
          Column(
            modifier = Modifier.padding(16.dp)
          ) {
            Text(list.name)
            list.items.forEach { item ->
              Text(
                modifier = Modifier.padding(start = 8.dp),
                text = item.text,
                style = MaterialTheme.typography.labelSmall
              )
            }
          }
        }
      }
    }
  }
}