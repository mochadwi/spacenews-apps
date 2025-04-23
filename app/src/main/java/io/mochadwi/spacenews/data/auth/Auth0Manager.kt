package io.mochadwi.spacenews.data.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import io.mochadwi.spacenews.R

@Singleton
class Auth0Manager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val auth0 = Auth0.getInstance(
        context.getString(R.string.com_auth0_client_id),
        context.getString(R.string.com_auth0_domain)
    )

    fun login(callback: (Result<Credentials>) -> Unit) {
        WebAuthProvider.login(auth0)
            .withScheme("https") // Replace with your app scheme
            .withScope("openid profile email")
            .withAudience("https://api.spaceflightnewsapi.net/v4/")
            .start(context, object : Callback<Credentials, AuthenticationException> {
                override fun onSuccess(result: Credentials) {
                    callback(Result.success(result))
                }

                override fun onFailure(error: AuthenticationException) {
                    callback(Result.failure(error))
                }
            })
    }

    fun logout() {
        WebAuthProvider.logout(auth0)
            .withScheme("demo") // Replace with your app scheme
            .start(context, object : Callback<Void?, AuthenticationException> {
                override fun onSuccess(result: Void?) {
                    // Handle successful logout
                }

                override fun onFailure(error: AuthenticationException) {
                    // Handle logout failure
                }
            })
    }

    fun getAccessToken(): String? {
        val encryptedPrefs = EncryptedSharedPreferences.create(
            "auth_prefs",
            "auth0_encryption_key",
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return encryptedPrefs.getString("access_token", null)
    }
}