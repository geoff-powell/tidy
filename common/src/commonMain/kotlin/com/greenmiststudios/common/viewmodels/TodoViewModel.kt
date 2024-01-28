package com.greenmiststudios.common.viewmodels

import com.greenmiststudios.common.data.TidyList

public data class TodoViewModel(
  val lists: List<TidyList>,
)

public sealed interface TodoViewEvent {
  public data object AddList : TodoViewEvent
  public data class OpenList(val id: String) : TodoViewEvent
}