package com.greenmiststudios.common.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.greenmiststudios.common.presenters.EditListPresenter
import com.greenmiststudios.common.screens.Screen
import com.greenmiststudios.common.screens.bindPresenter
import com.greenmiststudios.common.viewmodels.EditListViewEvent
import com.greenmiststudios.common.viewmodels.EditListViewEvent.AddItem
import com.greenmiststudios.common.viewmodels.EditListViewEvent.UpdateItemCompletion
import com.greenmiststudios.common.viewmodels.EditListViewEvent.UpdateList
import com.greenmiststudios.common.viewmodels.EditListViewModel

public data class EditListScreen(override val params: Config) : Screen<EditListScreen.Config> {
  public sealed interface Config {
    public data object CreateList : Config
    public data class EditList(val id: String) : Config
  }

  @Composable
  override fun Content() {
    val binding = bindPresenter<EditListPresenter, EditListViewModel, EditListViewEvent>()
    EditListScreen(viewModel = binding.viewModel, onEvent = binding.onEvent)
  }
}

@Composable
public fun EditListScreen(viewModel: EditListViewModel, onEvent: (EditListViewEvent) -> Unit) {
  LazyColumn(Modifier.padding(16.dp)) {
    item {
      when (viewModel) {
        is EditListViewModel.Add -> CreateListScreen(viewModel, onEvent)
        is EditListViewModel.Edit -> EditListContentScreen(viewModel, onEvent)
        EditListViewModel.Loading -> {
          Box(modifier = Modifier.height(128.dp)) {
            CircularProgressIndicator()
          }
          return@item
        }
      }

      Button(onClick = { onEvent(AddItem) }) {
        Text("Add Item")
      }

      val bottomSheetNavigator = LocalBottomSheetNavigator.current
      Button(
        modifier = Modifier
          .fillMaxWidth(),
        onClick = {
          onEvent(UpdateList)
          bottomSheetNavigator.hide()
        }
      ) {
        Text("Save")
      }
    }
  }
}

@Composable
private fun CreateListScreen(
  viewModel: EditListViewModel.Add,
  onEvent: (EditListViewEvent) -> Unit
) {
  Column {
    Text("Create List", style = MaterialTheme.typography.titleLarge)
    TextField(
      placeholder = { Text("New List") },
      value = viewModel.name ?: "",
      onValueChange = { onEvent(EditListViewEvent.UpdateName(it)) }
    )

    LazyColumn {
      items(viewModel.items.size) {
        val item = viewModel.items[it]
        Row {
          Checkbox(
            checked = item.completed,
            onCheckedChange = { checked ->
              onEvent(UpdateItemCompletion(item.id, checked))
            }
          )
          TextField(
            placeholder = { Text("Item") },
            value = item.text,
            onValueChange = { text -> onEvent(EditListViewEvent.UpdateItemText(item.id, text)) },
          )
        }
      }
    }
  }
}

@Composable
private fun EditListContentScreen(
  viewModel: EditListViewModel.Edit,
  onEvent: (EditListViewEvent) -> Unit
) {
  Column {
    Text("Edit List", style = MaterialTheme.typography.titleLarge)
    TextField(
      placeholder = { Text("New List") },
      value = viewModel.name,
      onValueChange = { onEvent(EditListViewEvent.UpdateName(it)) }
    )

    LazyColumn {
      items(viewModel.items.size) {
        val item = viewModel.items[it]
        Row {
          Checkbox(
            checked = item.completed,
            onCheckedChange = { checked ->
              onEvent(UpdateItemCompletion(item.id, checked))
            }
          )
          TextField(
            placeholder = { Text("Item") },
            value = item.text,
            onValueChange = { text -> onEvent(EditListViewEvent.UpdateItemText(item.id, text)) },
          )
        }
      }
    }
  }
}
