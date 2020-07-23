package com.example.githubproject.userinfo

import com.example.githubproject.common.BaseView
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo

interface UserInfoContract {
    interface View : BaseView {
        fun displayUserInfo(userInfo: UserInfo)
        fun displayUserRepos(userRepos: List<UserRepo>)
    }

    interface Presenter {
        fun getUser(userId: String)
        fun onViewDestroyed()
    }
}