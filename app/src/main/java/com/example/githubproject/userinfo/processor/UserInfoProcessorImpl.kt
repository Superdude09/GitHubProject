package com.example.githubproject.userinfo.processor

import com.example.githubproject.network.response.UserReposResponse
import com.example.githubproject.network.response.UserResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo
import javax.inject.Inject

class UserInfoProcessorImpl @Inject constructor(): UserInfoProcessor {
    override fun processUserInfo(userInfoDTO: UserResponse): UserInfo {
        // Other arbitrary processing logic could be placed here when creating the UserInfo object
        //  from the original DTO
        return UserInfo(userInfoDTO.name, userInfoDTO.avatarUrl)
    }

    override fun processUserRepo(userRepoDTO: UserReposResponse): UserRepo {
        // Other arbitrary processing logic could be placed here when creating the UserRepo object
        //  from the original DTO
        return UserRepo(
            userRepoDTO.name,
            userRepoDTO.description,
            userRepoDTO.updatedAt,
            userRepoDTO.stargazersCount,
            userRepoDTO.forks
        )
    }
}