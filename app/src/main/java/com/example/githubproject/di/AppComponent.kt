package com.example.githubproject.di

import com.example.githubproject.GitHubProjectApplication
import com.example.githubproject.network.ApiService
import com.example.githubproject.userinfo.UserInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class,
        PresenterModule::class]
)
interface AppComponent {
    fun getApiService(): ApiService

    fun injectApplication(application: GitHubProjectApplication)

    fun inject(userInfoFragment: UserInfoFragment)
}