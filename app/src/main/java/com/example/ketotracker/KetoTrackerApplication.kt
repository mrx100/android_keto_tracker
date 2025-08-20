package com.example.ketotracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Keto Tracker app
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection
 */
@HiltAndroidApp
class KetoTrackerApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
    }
}