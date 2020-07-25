package com.example.githubproject.di

import com.example.githubproject.network.ApiService
import com.example.githubproject.userinfo.UserInfoPresenter
import com.example.githubproject.userinfo.processor.UserInfoProcessorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun getUserInfoPresenter(apiService: ApiService, userInfoProcessor: UserInfoProcessorImpl) = UserInfoPresenter(apiService, userInfoProcessor)
}