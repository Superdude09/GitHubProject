package com.example.githubproject.search

interface SearchContract {
    interface View {

    }

    interface Presenter {
        fun getUser(userId: String)
        fun onViewDestroyed()
    }
}