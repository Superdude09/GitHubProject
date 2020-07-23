package com.example.githubproject.di

import com.example.githubproject.network.ApiService
import com.example.githubproject.userinfo.UserInfoPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun getUserInfoPresenter(apiService: ApiService) = UserInfoPresenter(apiService)
}