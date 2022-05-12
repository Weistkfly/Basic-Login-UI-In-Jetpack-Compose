package com.example.basicuserlogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicuserlogin.ui.theme.BasicUserLoginTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicUserLoginTheme {
                // A surface container using the 'background' color from the theme
                UserLogin(userName = "hi", password = "123" )
            }
        }
    }
}

@Composable
fun UserLogin(userName: String, password: String) {
    var user by remember { mutableStateOf(TextFieldValue("")) }
    var userPassword by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp)
    ) {
        Text(text = "Login into your account")
        OutlinedTextField(
            value = user,
            onValueChange = {user = it},
            label = { Text(text = "Username")},
            placeholder = {Text(text = "Here goes your username")}
        )
        OutlinedTextField(
            value = userPassword,
            onValueChange = {userPassword = it},
            label = { Text(text = "Password")},
            placeholder = {Text(text = "Here goes your password")}
        )
        Button(
            onClick = {
                if (userName == user.text && password == userPassword.text){
                    scope.launch {
                        snackbarHostState.showSnackbar("Logged in!")
                    }
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Credentials don't match")
                    }
                }
        }) {
            Text(text = "Login")
        }
        SnackbarHost(hostState = snackbarHostState)
    }
}
