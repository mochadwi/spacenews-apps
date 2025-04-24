package io.mochadwi.spacenews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.mochadwi.spacenews.data.auth.Auth0Manager
import io.mochadwi.spacenews.data.auth.AuthStateManager
import io.mochadwi.spacenews.presentation.navigation.AppNavigation
import io.mochadwi.spacenews.presentation.theme.SpaceNewsTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authStateManager: AuthStateManager

    @Inject
    lateinit var auth0Manager: Auth0Manager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authStateManager.setAuth0Manager(auth0Manager)
        setContent {
            SpaceNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        authStateManager.clearAuth0Manager()
    }
}