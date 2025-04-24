package io.mochadwi.spacenews.data.auth

import android.app.Activity
import android.content.Intent
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import io.mochadwi.spacenews.R

// we need Activity for auth0 to be able to start
// and manually provide this, if use @inject it'll be circular deps in AuthStateManager
class Auth0Manager(
    private val activity: Activity
) {
    private val auth0 = Auth0.getInstance(
        activity.getString(R.string.com_auth0_client_id),
        activity.getString(R.string.com_auth0_domain)
    )

    fun login(callback: (Result<Credentials>) -> Unit) {
        WebAuthProvider.login(auth0)
            .withScheme("space")
            .withScope("openid profile email")
            .withTrustedWebActivity()
            .withAudience("https://api.spaceflightnewsapi.net/v4/")
            .withParameters(mapOf("flags" to Intent.FLAG_ACTIVITY_NEW_TASK))
            .start(activity, object : Callback<Credentials, AuthenticationException> {
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
            .start(activity, object : Callback<Void?, AuthenticationException> {
                override fun onSuccess(result: Void?) {
                    // Handle successful logout
                }

                override fun onFailure(error: AuthenticationException) {
                    // Handle logout failure
                }
            })
    }

    // Remove redundant getAccessToken method
    /*
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
    */
}