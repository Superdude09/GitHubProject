package com.example.githubproject.network

import com.example.githubproject.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/users/{userId}")
    fun getUser(): Single<UserResponse>
}