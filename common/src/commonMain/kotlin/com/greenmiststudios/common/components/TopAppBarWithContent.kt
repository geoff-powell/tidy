package com.greenmiststudios.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.LocalNavigator
import com.greenmiststudios.common.providers.LocalStringManager
import com.greenmiststudios.common.resources.StringKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopAppBarWithContent(
  modifier: Modifier = Modifier,
  title: String = LocalStringManager.current[StringKey.APP_NAME],
  navigationIcon: Painter? = null,
  actionButtons: List<ActionButton> = emptyList(),
  content: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier) {
    TopAppBar(
      modifier = Modifier
        .fillMaxWidth(),
      title = { Text(title) },
      navigationIcon = {
        navigationIcon?.let {
          val navigator = LocalNavigator.current!!
          IconButton(onClick = { navigator.pop() }) {
            Icon(it, contentDescription = "Back")
          }
        }
      },
      actions = {
        actionButtons.forEach {
          if (it.icon != null) {
            IconButton(onClick = it.onClick) {
              Icon(it.icon, contentDescription = it.action)
            }
          } else {
            Button(onClick = it.onClick) {
              Text(it.action)
            }
          }
        }
      }
    )

    content()
  }
}

@Stable
public data class ActionButton(
  val action: String,
  val icon: Painter? = null,
  val onClick: () -> Unit
)