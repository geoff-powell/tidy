package com.greenmiststudios.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.greenmiststudios.common.providers.LocalStringManager
import com.greenmiststudios.common.resources.StringKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopAppBarWithContent(
  modifier: Modifier = Modifier,
  title: String = LocalStringManager.current[StringKey.APP_NAME],
  actionButtons: List<ActionButton> = emptyList(),
  content: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier) {
    TopAppBar(
      modifier = Modifier
        .fillMaxWidth(),
      title = { Text(title) },
      actions = {
        actionButtons.forEach {
          // TODO: Support Icons
          // NavigationBarItem(
          //   icon = { Icon(it.action, contentDescription = it.action) },
          //   onClick = it.onClick
          // )

          Button(onClick = it.onClick) {
            Text(it.action)
          }
        }
      }
    )

    content()
  }
}

@Stable
public data class ActionButton(val action: String, val onClick: () -> Unit)