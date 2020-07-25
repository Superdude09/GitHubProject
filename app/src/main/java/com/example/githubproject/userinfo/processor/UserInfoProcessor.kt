package com.example.githubproject.userinfo.processor

import com.example.githubproject.network.response.UserReposResponse
import com.example.githubproject.network.response.UserResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo

interface UserInfoProcessor {
    fun processUserInfo(userInfoDTO: UserResponse): UserInfo
    fun processUserRepo(userRepoDTO: UserReposResponse): UserRepo
}