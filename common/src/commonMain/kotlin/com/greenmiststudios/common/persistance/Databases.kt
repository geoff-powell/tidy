package com.greenmiststudios.common.persistance

import com.greenmiststudios.common.data.TidyList
import com.greenmiststudios.tidy.TidyListQueries


public val USER_ID: String = "USER"

public fun TidyListQueries.updateList(tidyList: TidyList) {
  updateListName(name = tidyList.name, id = tidyList.id)
  tidyList.items.forEach {
    addOrUpdateListItem(it.id, tidyList.id, it.text, it.completed, USER_ID)
  }
}

public fun TidyListQueries.createList(tidyList: TidyList) {
  addList(id = tidyList.id, name = tidyList.name, "", USER_ID)
  tidyList.items.forEach {
    addOrUpdateListItem(it.id, tidyList.id, it.text, it.completed,USER_ID)
  }
}