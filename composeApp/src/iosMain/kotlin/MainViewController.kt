import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.window.ComposeUIViewController
import com.greenmiststudios.common.UIShow
import com.greenmiststudios.common.di.startTidyKoin
import platform.UIKit.UIViewController

// Entry point for iOS
public fun MainViewController(): UIViewController {
  startTidyKoin()
  return ComposeUIViewController {
    MaterialTheme(
      colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
      UIShow()
    }
  }
}
