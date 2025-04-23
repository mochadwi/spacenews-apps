package io.mochadwi.spacenews.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.mochadwi.spacenews.presentation.navigation.Routes

@Composable
fun AuthScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (val state = authState) {
            is AuthState.Success -> {
                // Navigate to home screen on successful login
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.AUTH) { inclusive = true } // Clear back stack up to AuthScreen
                }
                viewModel.resetState() // Reset state after navigation
            }
            is AuthState.Error -> {
                // Show error message
                Toast.makeText(context, "Login Failed: ${state.message}", Toast.LENGTH_LONG).show()
                viewModel.resetState() // Reset state after showing error
            }
            else -> { /* Idle or Loading */ }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (authState) {
            is AuthState.Loading -> {
                CircularProgressIndicator()
            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Welcome", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.login() }) {
                        Text("Login / Register with Auth0")
                    }
                }
            }
        }
    }
}