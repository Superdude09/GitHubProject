package com.example.githubproject.userinfo

import com.example.githubproject.common.BaseView
import com.example.githubproject.userinfo.model.UserInfo

interface UserInfoContract {
    interface View : BaseView {
        fun displayUserInfo(userInfo: UserInfo)
    }

    interface Presenter {
        fun getUser(userId: String)
        fun onViewDestroyed()
    }
}