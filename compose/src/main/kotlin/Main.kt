import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import team.jvav.stuhub.core.data.DAO
import team.jvav.stuhub.core.data.DAO.createGroupInClass
import team.jvav.stuhub.core.data.DAO.createStudentInClass
import team.jvav.stuhub.core.data.DataSource
import team.jvav.stuhub.core.util.RandomUtil

@Composable
@Preview
fun App() {
    var randomResult by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    MaterialTheme {
        Column {
            TopAppBar(title = { Text("StuHub") })
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Button(onClick = {
                    randomResult = RandomUtil.getRandomStudentFromClass(RandomUtil.getRandomClass().id).toString()
                    showDialog = true
                }) {
                    Text("从随机班级中抽取随机学生")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            buttons = {
                TextButton(onClick = { showDialog = false }) {
                    Text("关闭")
                }
            },
            title = { Text("抽取结果") },
            text = { Text(randomResult) }
        )
    }
}

fun main() {
    if (!DataSource.exists()) {
        DataSource.init()
        DAO.createClass(1)

        // 初始化组和学生
        for (i in 0..99) {
            createGroupInClass(1, i + 1, "Group" + (i + 1))
            for (j in 0..9) {
                createStudentInClass(1, i * 10 + j, "Stu" + (i * 10 + j))
                DAO.addStudentToGroup(1, i + 1, i * 10 + j)
            }
        }
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "StuHub") {
            App()
        }
    }
}