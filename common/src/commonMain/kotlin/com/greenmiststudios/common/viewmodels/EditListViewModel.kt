package com.greenmiststudios.common.viewmodels

import com.greenmiststudios.common.data.TidyListItem

public sealed interface EditListViewModel {
  public data class Add(
    val name: String?,
    val items: List<TidyListItem> = emptyList(),
  ) : EditListViewModel

  public data class Edit(
    val name: String,
    val items: List<TidyListItem> = emptyList(),
  ) : EditListViewModel

  public data object Loading : EditListViewModel
}

public sealed interface EditListViewEvent {
  public data object UpdateList: EditListViewEvent

  public data object AddItem : EditListViewEvent
  public data class UpdateName(val name: String) : EditListViewEvent
  public data class UpdateItemCompletion(val id: String, val completed: Boolean) : EditListViewEvent
  public data class UpdateItemText(val id: String, val text: String) : EditListViewEvent
}