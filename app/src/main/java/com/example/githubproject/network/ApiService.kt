package com.example.githubproject.network

import com.example.githubproject.userinfo.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: String): Single<UserResponse>
}