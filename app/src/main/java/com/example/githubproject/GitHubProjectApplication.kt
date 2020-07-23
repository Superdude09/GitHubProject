package com.example.githubproject

import android.app.Activity
import android.app.Application
import com.example.githubproject.di.AppComponent
import com.example.githubproject.di.DaggerAppComponent
import timber.log.Timber

class GitHubProjectApplication : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        fun get(activity: Activity) : GitHubProjectApplication = activity.application as GitHubProjectApplication
    }

    override fun onCreate() {
        super.onCreate()
        initInjection()
        Timber.plant(Timber.DebugTree())
    }

    fun getAppComponent() = appComponent

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().build()
        appComponent.injectApplication(this)
    }
 }