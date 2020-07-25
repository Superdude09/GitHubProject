package com.example.githubproject.di

import com.example.githubproject.userinfo.processor.UserInfoProcessorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProcessorModule {
    @Provides
    @Singleton
    fun getUserInfoProcessor() = UserInfoProcessorImpl()
}