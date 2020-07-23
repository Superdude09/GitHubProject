package com.example.githubproject.model

import com.google.gson.annotations.SerializedName

data class UserResponse(@field:SerializedName("name") val name: String,
                        @field:SerializedName("avatar_url")val avatarUrl: String)