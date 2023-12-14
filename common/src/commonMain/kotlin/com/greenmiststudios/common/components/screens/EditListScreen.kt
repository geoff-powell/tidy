package com.greenmiststudios.common.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.greenmiststudios.common.components.TopAppBarWithContent
import com.greenmiststudios.common.presenters.EditListPresenter
import com.greenmiststudios.common.screens.Screen
import com.greenmiststudios.common.screens.bindPresenter
import com.greenmiststudios.common.viewmodels.EditListViewEvent
import com.greenmiststudios.common.viewmodels.EditListViewEvent.AddNewItem
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
public fun EditListScreen(viewModel: EditListViewModel, onEvent: (EditListViewEvent) -> Unit) {
  TopAppBarWithContent(
    modifier = Modifier.fillMaxSize(),
    title = viewModel.title,
    navigationIcon = rememberVectorPainter(Icons.Default.ArrowBack),
  ) {
    Column(Modifier.padding(16.dp).fillMaxSize()) {
      Column(Modifier.fillMaxWidth().weight(1f)) {
        when (viewModel) {
          is EditListViewModel.Loaded.Add -> CreateListScreen(viewModel, onEvent)
          is EditListViewModel.Loaded.Edit -> EditListContentScreen(viewModel, onEvent)
          EditListViewModel.Loading -> {
            Box(modifier = Modifier.height(128.dp)) {
              CircularProgressIndicator()
            }
            return@Column
          }
        }

        require(viewModel is EditListViewModel.Loaded)
      }

      val navigator = LocalNavigator.current!!
      Button(
        modifier = Modifier
          .fillMaxWidth(),
        onClick = {
          onEvent(UpdateList)
          navigator.pop()
        }
      ) {
        Text("Save")
      }
    }
  }
}

@Composable
private fun CreateListScreen(
  viewModel: EditListViewModel.Loaded.Add,
  onEvent: (EditListViewEvent) -> Unit
) {
  Column {
    TextField(
      placeholder = { Text("List Name") },
      value = viewModel.name,
      onValueChange = { onEvent(EditListViewEvent.UpdateName(it)) }
    )

    TextField(
      modifier = Modifier
        .fillMaxWidth(),
      leadingIcon = { Icon(Icons.Rounded.Add, contentDescription = "Add Item") },
      placeholder = { Text("Add Item") },
      value = viewModel.newItemText,
      onValueChange = { text -> onEvent(EditListViewEvent.UpdateNewItemText(text)) },
      keyboardActions = KeyboardActions(
        onDone = { onEvent(AddNewItem(viewModel.newItemText)) },
      ),
      keyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Sentences,
        imeAction = ImeAction.Done
      )
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
  viewModel: EditListViewModel.Loaded.Edit,
  onEvent: (EditListViewEvent) -> Unit
) {
  Column {
    Text(text = viewModel.name, style = MaterialTheme.typography.titleLarge)

    TextField(
      modifier = Modifier
        .padding(top = 16.dp)
        .fillMaxWidth(),
      leadingIcon = { Icon(Icons.Rounded.Add, contentDescription = "Add Item") },
      placeholder = { Text("Add Item") },
      value = viewModel.newItemText,
      onValueChange = { text -> onEvent(EditListViewEvent.UpdateNewItemText(text)) },
      keyboardActions = KeyboardActions(
        onDone = { onEvent(AddNewItem(viewModel.newItemText)) },
      ),
      keyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Sentences,
        imeAction = ImeAction.Done
      )
    )

    LazyColumn {
      items(viewModel.items.size) {
        val item = viewModel.items[it]
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(
            checked = item.completed,
            onCheckedChange = { checked ->
              onEvent(UpdateItemCompletion(item.id, checked))
            },
          )
          Text(item.text)
        }
      }
    }
  }
}
