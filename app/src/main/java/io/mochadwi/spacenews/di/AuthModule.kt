package io.mochadwi.spacenews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mochadwi.spacenews.data.auth.Auth0Manager
import io.mochadwi.spacenews.data.auth.SecureTokenStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuth0Manager(auth0Manager: Auth0Manager): Auth0Manager = auth0Manager

    @Provides
    @Singleton
    fun provideSecureTokenStorage(secureTokenStorage: SecureTokenStorage): SecureTokenStorage = secureTokenStorage
}