package com.example.ssbookstore

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = "SS Bookstore")
                }
            )
        }
    ) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {

                var userId by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .focusRequester(focusRequester),
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text("User ID") }
                )

                LaunchedEffect(focusRequester) {
                    focusRequester.requestFocus()
                    keyboard?.show()
                }

                var password by remember { mutableStateOf("")}
                var isPasswordVisible by remember { mutableStateOf(false) }

                OutlinedTextField(
                    modifier = Modifier.padding(2.dp),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (isPasswordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    }
                )

                Button(onClick = {
                    Log.i("Login","$userId $password")

                    if (userId == "SS" && password == "11111"){
                        navController.navigate("listing-screen")
                    } else {
                        Toast.makeText(context, "Incorrect username or password", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Login")
                }
            }
    }
}