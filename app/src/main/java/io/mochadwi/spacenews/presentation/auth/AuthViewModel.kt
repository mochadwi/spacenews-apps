package io.mochadwi.spacenews.presentation.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.result.Credentials
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mochadwi.spacenews.data.auth.Auth0Manager
import io.mochadwi.spacenews.data.auth.AuthStateManager
import io.mochadwi.spacenews.data.auth.SecureTokenStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authStateManager: AuthStateManager,
    @ApplicationContext private val context: Context // Auth0Manager needs context
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authStateManager.login { result ->
                result.onSuccess {
                    _authState.value = AuthState.Success
                }.onFailure {
                    exception ->
                    _authState.value = AuthState.Error(exception.localizedMessage ?: "Unknown login error")
                }
            }
        }
    }

    fun logout() {
        // Implement logout logic if needed, clearing tokens
        authStateManager.logout()
        _authState.value = AuthState.Idle // Reset state after logout
    }

    fun checkLoginStatus() {
        // Optionally check if already logged in on ViewModel init
        // might no need, as this already handled inside AuthStateManager.init
        // if (accessToken != null) {
            // Potentially validate token or fetch user profile
            // For now, assume valid if token exists
            // _authState.value = AuthState.Success( /* Need a way to reconstruct or indicate logged in */ )
            // This part needs refinement based on how you want to handle existing sessions
        // }
    }

    // Call this function to reset the state, e.g., after navigating away
    fun resetState() {
        _authState.value = AuthState.Idle
    }
}