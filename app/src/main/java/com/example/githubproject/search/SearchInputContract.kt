package com.example.githubproject.search

interface SearchInputContract {
    interface View {

    }

    interface Presenter {
        fun getUser(userId: String)
        fun onViewDestroyed()
    }
}