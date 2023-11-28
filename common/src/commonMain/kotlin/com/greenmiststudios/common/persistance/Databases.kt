package com.greenmiststudios.common.persistance

import com.greenmiststudios.common.data.TidyList
import com.greenmiststudios.tidy.TidyListQueries

public fun TidyListQueries.updateList(tidyList: TidyList) {
  updateListName(name = tidyList.name, id = tidyList.id)
  tidyList.items.forEach {
    addOrUpdateListItem(it.id, tidyList.id, it.text, it.completed,"USER")
  }
}