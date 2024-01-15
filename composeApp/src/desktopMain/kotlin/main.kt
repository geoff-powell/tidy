import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.greenmiststudios.common.UIShow
import com.greenmiststudios.common.di.appModule
import com.greenmiststudios.common.di.startTidyKoin
import org.koin.compose.KoinApplication
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools

fun main() {
  startTidyKoin()

  application {
    Window(onCloseRequest = ::exitApplication, title = "Tidy") {
      UIShow()
    }
  }
}

@Preview
@Composable
fun AppDesktopPreview() {
  if (KoinPlatformTools.defaultContext().getOrNull() == null) {
    startTidyKoin()
  }

  UIShow()
}