package com.greenmiststudios.common.data

import androidx.compose.runtime.Stable
import com.greenmiststudios.tidy.GetListWithItems
import com.greenmiststudios.tidy.TidyList as DBTidyList

@Stable
public data class TidyList(
  val id: String,
  val name: String,
  val items: List<TidyListItem>,
)

@Stable
public data class TidyListItem(
  val id: String,
  val text: String,
  val completed: Boolean = false,
  val assignees: List<CustomerId> = emptyList(), // TODO: Add support
)

public fun DBTidyList.asTidyList(): TidyList = TidyList(
  id = id,
  name = name,
  items = emptyList()
)

public fun List<GetListWithItems>.asTidyList(): TidyList? {
  if (isEmpty()) return null

  val list = first()
  return TidyList(
    id = list.id,
    name = list.name,
    items = mapNotNull { item ->
      item.list_id?.let {
        TidyListItem(
          id = item.id_!!,
          text = item.text!!,
          completed = item.completed!!,
          assignees = emptyList(),
        )
      }
    }
  )
}