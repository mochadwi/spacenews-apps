package io.mochadwi.spacenews.data.remote.interceptor

import io.mochadwi.spacenews.data.auth.SecureTokenStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val secureTokenStorage: SecureTokenStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Skip authentication for non-authenticated endpoints if needed
        if (originalRequest.url.encodedPath.contains("/public/")) {
            return chain.proceed(originalRequest)
        }

        val accessToken: String? = secureTokenStorage.getAccessToken()

        return if (accessToken != null) {
            val authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(authenticatedRequest)
        } else {
            // Proceed with original request if no token is available
            chain.proceed(originalRequest)
        }
    }
}