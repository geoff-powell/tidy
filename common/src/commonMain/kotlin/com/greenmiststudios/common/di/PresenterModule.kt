package com.greenmiststudios.common.di

import com.greenmiststudios.common.di.qualifiers.Threading.IO
import com.greenmiststudios.common.presenters.EditListPresenter
import com.greenmiststudios.common.presenters.HomePresenter
import com.greenmiststudios.common.presenters.ListsPresenter
import org.koin.core.module.Module
import org.koin.dsl.module

public val presenterModule: Module = module {
  factory { EditListPresenter(screen = get(), database = get(), ioContext = get(IO)) }
  factory { ListsPresenter(database = get(), ioContext = get(IO)) }
  factory { HomePresenter() }
}