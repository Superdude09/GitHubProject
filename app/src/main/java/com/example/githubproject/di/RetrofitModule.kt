package com.example.githubproject.di

import ApplicationScope
import com.example.githubproject.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")     // TODO: externalize/inject?
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}