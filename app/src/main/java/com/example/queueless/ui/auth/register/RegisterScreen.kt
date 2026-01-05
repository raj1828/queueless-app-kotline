package com.example.queueless.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.queueless.data.remote.dto.OtpFlow
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val registerSuccess by authViewModel.registerSuccess.collectAsState()
    val loading by authViewModel.loading.collectAsState()
    val error by authViewModel.error.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(registerSuccess) {
        if (registerSuccess) {
            navController.navigate(
                Routes.Otp.createRoute(OtpFlow.REGISTER)
            )
            authViewModel.resetLoginState()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
    ) {

        /* 🔵 TOP HEADER (MATCHING IMAGE THEME) */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    color = Color(0xFF2F66F3),
                    shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Register to start using QueueLess",
                    color = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        /* ⚪ FORM CARD */
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(y = (-40).dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                /* 🔹 Full Name */
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                /* 🔹 Email */
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                /* 🔹 Password */
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                /* 🔹 REGISTER BUTTON */
                Button(
                    onClick = {
                        authViewModel.register(name, email, password, "USER")
                        /*
                          API BODY:
                          {
                            name,
                            email,
                            password,
                            role = "USER"
                          }
                          -> Send OTP
                         */
//                        navController.navigate(Routes.Otp.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F66F3)
                    )
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text("Register & Send OTP", fontWeight = FontWeight.Bold)
                    }
                }

                error?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                /* 🔹 LOGIN NAVIGATION */
                TextButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Already have an account? Login")
                }
            }
        }
    }
}
