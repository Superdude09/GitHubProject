package com.example.githubproject.userinfo.processor

import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import org.junit.Test

import org.junit.Assert.*

class UserInfoProcessorImplTest {

    private val processor = UserInfoProcessorImpl()

    @Test
    fun processUserInfo() {
        val name = "some name"
        val avatarUrl = "https://www.somewhere.com/img.png"

        val userInfoResponse = UserInfoResponse(name, avatarUrl)
        val expectedUserInfo = UserInfo(name, avatarUrl)

        val actualResult = processor.processUserInfo(userInfoResponse)

        assertTrue(expectedUserInfo.name == actualResult.name)
        assertTrue(expectedUserInfo.avatarUrl == actualResult.avatarUrl)
    }

    @Test
    fun processUserRepo() {
        val name = "some repo name"
        val description = "some repo description"
        val updatedAt = "2020-06-27T05:52:40Z"
        val stargazersCount = 6
        val forks = 12

        val userRepoInfoResponse = UserRepoInfoResponse(name, description, updatedAt, stargazersCount, forks)
        val expectedUserRepoInfo = UserRepoInfo(name, description, updatedAt, stargazersCount, forks)

        val actualResult = processor.processUserRepoInfo(userRepoInfoResponse)

        assertTrue(expectedUserRepoInfo.name == actualResult.name)
        assertTrue(expectedUserRepoInfo.description == actualResult.description)
        assertTrue(expectedUserRepoInfo.updatedAt == actualResult.updatedAt)
        assertTrue(expectedUserRepoInfo.stargazersCount == actualResult.stargazersCount)
        assertTrue(expectedUserRepoInfo.forks == actualResult.forks)
    }
}