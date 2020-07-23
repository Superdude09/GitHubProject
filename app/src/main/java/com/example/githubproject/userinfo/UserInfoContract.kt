package com.example.githubproject.userinfo

interface UserInfoContract {
    interface View {

    }

    interface Presenter {
        fun getUser(userId: String)
        fun onViewDestroyed()
    }
}