package com.example.githubproject.userinfo.processor

import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import javax.inject.Inject

class UserInfoProcessorImpl @Inject constructor(): UserInfoProcessor {
    override fun processUserInfo(userInfoDTO: UserInfoResponse): UserInfo {
        // Other arbitrary processing logic could be placed here when creating the UserInfo object
        //  from the original DTO
        return UserInfo(userInfoDTO.name, userInfoDTO.avatarUrl)
    }

    override fun processUserRepo(userRepoDTO: UserRepoInfoResponse): UserRepoInfo {
        // Other arbitrary processing logic could be placed here when creating the UserRepo object
        //  from the original DTO
        return UserRepoInfo(
            userRepoDTO.name,
            userRepoDTO.description,
            userRepoDTO.updatedAt,
            userRepoDTO.stargazersCount,
            userRepoDTO.forks
        )
    }
}