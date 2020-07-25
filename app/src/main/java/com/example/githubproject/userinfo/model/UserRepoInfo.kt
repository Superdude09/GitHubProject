package com.example.githubproject.userinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoInfo(val name : String?,
                        val description : String?,
                        val updatedAt : String?,
                        val stargazersCount: Int?,
                        val forks: Int?) : Parcelable