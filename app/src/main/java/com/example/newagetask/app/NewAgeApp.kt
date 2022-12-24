package com.example.newagetask.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewAgeApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
