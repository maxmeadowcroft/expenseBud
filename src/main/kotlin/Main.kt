import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication {
    MaterialTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {
    var textFieldValue by remember { mutableStateOf("") }
    var numberFieldValue by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expense Tracker") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Welcome to the Expense Tracker!")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Navigate through the app via the sidebar.")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Track Your Expenses", fontSize = 24.sp, style = MaterialTheme.typography.h4)

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                    ) {
                        if (textFieldValue.isEmpty()) Text("Enter description...")
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = numberFieldValue,
                onValueChange = { numberFieldValue = it },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                    ) {
                        if (numberFieldValue.isEmpty()) Text("Enter amount...")
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    showSnackbar = true
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Add Expense")
            }

            if (showSnackbar) {
                Snackbar(
                    action = {
                        TextButton(onClick = { showSnackbar = false }) {
                            Text("Dismiss")
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Expense Added!")
                }
            }
        }
    }
}
