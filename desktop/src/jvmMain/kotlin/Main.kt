import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.greenmiststudios.common.UIShow
import com.greenmiststudios.common.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
  startKoin {
    modules(appModule())
  }

  Window(onCloseRequest = ::exitApplication) {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
      UIShow()
    }
  }
}
