import androidx.compose.ui.window.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.application
import com.github.tehras.charts.ui.ChartsApp

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            ChartsApp()
        }
    }
}