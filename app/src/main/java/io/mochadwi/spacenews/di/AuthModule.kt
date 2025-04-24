package io.mochadwi.spacenews.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import io.mochadwi.spacenews.data.auth.Auth0Manager

@Module
@InstallIn(ActivityComponent::class)
object AuthModule {
    @Provides
    @ActivityScoped
    fun provideAuth0Manager(activity: Activity): Auth0Manager = Auth0Manager(activity)
}