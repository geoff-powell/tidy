package com.greenmiststudios.common.viewmodels

import com.greenmiststudios.common.data.TidyList

public data class ListsViewModel(
  val lists: List<TidyList>,
)

public sealed interface ListsViewEvent {
  public data object AddList : ListsViewEvent
  public data class OpenList(val id: String) : ListsViewEvent
}