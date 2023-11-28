import androidx.compose.ui.window.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.greenmiststudios.common.UIShow
import com.greenmiststudios.common.di.appModule
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin

public fun main() {
  onWasmReady {
    startKoin {
      modules(appModule())
    }

    Window("tidy") {
      MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
        UIShow()
      }
    }
  }
}