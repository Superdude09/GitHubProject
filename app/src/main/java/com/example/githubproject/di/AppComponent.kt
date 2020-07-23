package com.example.githubproject.di

import ApplicationScope
import com.example.githubproject.GitHubProjectApplication
import com.example.githubproject.network.ApiService
import dagger.Component

//@ApplicationScope
@Component(
    modules = [RetrofitModule::class,
        SearchActivityModule::class]
)
interface AppComponent {
    fun getApiService(): ApiService

    fun injectApplication(application: GitHubProjectApplication)
}