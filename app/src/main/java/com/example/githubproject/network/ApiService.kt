package com.example.githubproject.network

import com.example.githubproject.network.response.UserReposResponse
import com.example.githubproject.network.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: String): Single<UserResponse>

    @GET("/users/{userId}/repos")
    fun getUserPublicRepos(@Path("userId") userId: String): Single<List<UserReposResponse>>
}