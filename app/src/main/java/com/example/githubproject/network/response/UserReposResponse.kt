package com.example.githubproject.network.response

import com.google.gson.annotations.SerializedName

data class UserReposResponse(@field:SerializedName("name") val name : String?,
                             @field:SerializedName("description") val description : String?,
                             @field:SerializedName("updated_at") val updatedAt : String?,
                             @field:SerializedName("stargazers_count") val stargazersCount: Int?,
                             @field:SerializedName("forks") val forks: Int?)