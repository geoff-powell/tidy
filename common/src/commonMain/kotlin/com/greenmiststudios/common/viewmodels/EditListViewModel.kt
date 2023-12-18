package com.greenmiststudios.common.viewmodels

import com.greenmiststudios.common.data.TidyListItem

public sealed interface EditListViewModel {
  public val title: String

  public sealed class Loaded(
    override val title: String,
    public val name: String,
    public val newItemText: String = "",
    public val items: List<TidyListItem> = emptyList(),
  ) : EditListViewModel {
    public class Add(
      title: String,
      name: String,
      newItemText: String = "",
      items: List<TidyListItem> = emptyList(),
    ) : Loaded(title, name, newItemText, items)

    public class Edit(
      title: String,
      name: String,
      newItemText: String = "",
      items: List<TidyListItem> = emptyList(),
    ) : Loaded(title, name, newItemText, items)
  }

  public data object Loading : EditListViewModel {
    override val title: String = ""
  }
}

public sealed interface EditListViewEvent {
  public data object UpdateList : EditListViewEvent
  public data object DeleteList : EditListViewEvent

  public data object AddItem : EditListViewEvent
  public data class AddNewItem(val text: String) : EditListViewEvent
  public data class UpdateName(val name: String) : EditListViewEvent
  public data class UpdateItemCompletion(val id: String, val completed: Boolean) : EditListViewEvent
  public data class UpdateItemText(val id: String, val text: String) : EditListViewEvent
  public data class UpdateNewItemText(val text: String) : EditListViewEvent
}