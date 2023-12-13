package com.dev.pokedex.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dev.pokedex.app.presentation.view_model.SampleViewModel

@Composable
fun LoginScreen(navigateToHome:  () -> Unit) {
    Surface {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFDE0024))
                .padding(20.dp)
        ) {
            Text(
                text = "Pokédex",
                style = TextStyle.Default.copy(
                    fontSize = 72.sp,
                    color = Color.Yellow,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Box(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column {
                    UsernameTextField()
                    PasswordTextField()
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = { navigateToHome() }) {
                        Text(text = "Login", fontSize = 16.sp)
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Register",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Cyan,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { /* Handle Register click */ }
                )
                Text(
                    text = "|",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Cyan,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Forgot Password",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Cyan,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { /* Handle Forgot Password click */ }
                )
            }
        }
    }
}
@Composable
fun UsernameTextField() {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Username") },
        placeholder = { Text("Enter Username")},
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun PasswordTextField() {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Password") },
        placeholder = { Text("Enter Password")},
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
