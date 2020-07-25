package com.example.githubproject.userinfo.processor

import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo

interface UserInfoProcessor {
    fun processUserInfo(userInfoDTO: UserInfoResponse): UserInfo
    fun processUserRepo(userRepoDTO: UserRepoInfoResponse): UserRepoInfo
}