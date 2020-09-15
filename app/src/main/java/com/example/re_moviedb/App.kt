package com.example.re_moviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@Suppress("Unused")
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}