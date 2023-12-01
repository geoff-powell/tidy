package com.greenmiststudios.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.greenmiststudios.common.providers.LocalStringManager
import com.greenmiststudios.common.resources.RealStringManager
import com.greenmiststudios.common.screens.Tab
import org.koin.compose.KoinContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun App() {
  Box(
    modifier =
    Modifier.fillMaxSize()
      .statusBarsPadding()
      .navigationBarsPadding()
  ) {
    KoinContext {
      MaterialTheme(
        shapes = Shapes(
          small = RoundedCornerShape(4.dp),
          medium = RoundedCornerShape(8.dp),
          large = RoundedCornerShape(16.dp),
        )
      ) {
        CompositionLocalProvider(
          LocalStringManager provides RealStringManager(),
        ) {
          BottomSheetNavigator(
            sheetShape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp),
          ) {
            TabNavigator(Tab.Home) {
              Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                  NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                  ) {
                    TabItem(Tab.Home)
                    TabItem(Tab.Lists)
                    TabItem(Tab.Calendar)
                  }
                },
              ) {
                Box(
                  modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                  contentAlignment = Alignment.Center
                ) {
                  CurrentTab()
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
public fun RowScope.TabItem(tab: Tab) {
  val tabNavigator = LocalTabNavigator.current
  NavigationBarItem(
    selected = tabNavigator.current == tab,
    icon = {
      tab.options.icon?.let {
        Icon(it, contentDescription = tab.options.title)
      }
    },
    label = { Text(tab.options.title) },
    onClick = {
      tabNavigator.current = tab
    },
  )
}