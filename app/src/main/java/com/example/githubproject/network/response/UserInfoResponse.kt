package com.example.githubproject.network.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(@field:SerializedName("name") val name: String?,
                            @field:SerializedName("avatar_url")val avatarUrl: String?)