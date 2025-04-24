package io.mochadwi.spacenews.data.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
// import androidx.work.ExistingWorkPolicy
// import androidx.work.OneTimeWorkRequestBuilder
// import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
// import io.mochadwi.spacenews.data.worker.AutoLogoutWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthStateManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val secureTokenStorage: SecureTokenStorage
) {
    private var auth0Manager: Auth0Manager? = null

    fun setAuth0Manager(manager: Auth0Manager) {
        auth0Manager = manager
    }

    fun clearAuth0Manager() {
        auth0Manager = null
    }

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    companion object {
        private const val AUTO_LOGOUT_WORK_NAME = "auto_logout_work"
        private const val AUTO_LOGOUT_DELAY_HOURS = 24L
    }

    init {
        checkAuthenticationState()
    }

    private fun checkAuthenticationState() {
        val hasValidToken = !secureTokenStorage.isTokenExpired() && 
                           secureTokenStorage.getAccessToken() != null
        _isAuthenticated.value = hasValidToken

        if (hasValidToken) {
            scheduleAutoLogout()
        }
    }

    fun login(callback: (Result<Unit>) -> Unit) {
        auth0Manager?.login { result ->
            result.onSuccess { credentials ->
                secureTokenStorage.saveTokens(
                    accessToken = credentials.accessToken,
                    idToken = credentials.idToken,
                    refreshToken = credentials.refreshToken ?: "",
                    expiresIn = credentials.expiresAt.toInstant().epochSecond,
                )
                _isAuthenticated.value = true
                scheduleAutoLogout()
                callback(Result.success(Unit))
            }.onFailure { error ->
                callback(Result.failure(error))
            }
        }
    }

    fun logout() {
        auth0Manager?.logout()
        secureTokenStorage.clearTokens()
        _isAuthenticated.value = false
        cancelAutoLogout()
    }

    
    private fun scheduleAutoLogout() {
        /*
        val workRequest = OneTimeWorkRequestBuilder<AutoLogoutWorker>()
            .setInitialDelay(AUTO_LOGOUT_DELAY_HOURS, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                AUTO_LOGOUT_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        */
    }

    private fun cancelAutoLogout() {
        /*
        WorkManager.getInstance(context)
            .cancelUniqueWork(AUTO_LOGOUT_WORK_NAME)
        */
    }
}