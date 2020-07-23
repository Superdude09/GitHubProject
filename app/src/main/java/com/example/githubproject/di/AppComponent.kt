package com.example.githubproject.di

import ApplicationScope
import com.example.githubproject.GitHubProjectApplication
import dagger.Component

//@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun injectApplication(application: GitHubProjectApplication)
}