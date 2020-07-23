package com.example.githubproject

import android.app.Application
import com.example.githubproject.di.AppComponent
import com.example.githubproject.di.DaggerAppComponent

class GitHubProjectApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().build()

        appComponent.injectApplication(this)
    }
 }