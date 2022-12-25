package com.example.newagetask.features.add_bmi_details.presentation.di

import com.example.newagetask.common.google_ads.GoogleAds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    fun provideGoogleAdsClass(): GoogleAds {
        return GoogleAds()
    }
}