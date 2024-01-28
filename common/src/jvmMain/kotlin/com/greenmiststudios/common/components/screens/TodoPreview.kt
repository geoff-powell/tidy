package com.greenmiststudios.common.components.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.greenmiststudios.common.data.TidyList
import com.greenmiststudios.common.data.TidyListItem
import com.greenmiststudios.common.di.startTidyKoin
import com.greenmiststudios.common.uiutils.LoggingEventReceiver
import com.greenmiststudios.common.uiutils.TidyPreview
import com.greenmiststudios.common.viewmodels.TodoViewModel
import org.koin.mp.KoinPlatformTools

@Preview
@Composable
public fun TodoPreview(): Unit = TidyPreview {
  if (KoinPlatformTools.defaultContext().getOrNull() == null) {
    startTidyKoin()
  }

  TodoScreen(
    todoViewModel = TodoViewModel(emptyList()),
    onEvent = LoggingEventReceiver(),
  )
}

@Preview
@Composable
public fun TodoPreviewWithItems(): Unit = TidyPreview {
  if (KoinPlatformTools.defaultContext().getOrNull() == null) {
    startTidyKoin()
  }

  TodoScreen(
    todoViewModel = TodoViewModel(listOf(
      TidyList("1", "List 1", emptyList()),
      TidyList("2", "List 2", listOf(TidyListItem("1", "Item 1", false))),
      TidyList("3", "List 3", emptyList()),
    )),
    onEvent = LoggingEventReceiver(),
  )
}