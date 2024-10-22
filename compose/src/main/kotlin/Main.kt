import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import team.jvav.stuhub.core.data.model.Class

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    val c = Class(1)

    MaterialTheme {
        Column {
            TopAppBar(title = { Text("StuHub") })
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Button(onClick = {  }){
                    Text("添加班级")
                }
                Button(onClick = {  }){
                    Text("查看班级")
                }
                Button(onClick = {  }){
                    Text("删除班级")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "StuHub") {
        App()
    }
}
