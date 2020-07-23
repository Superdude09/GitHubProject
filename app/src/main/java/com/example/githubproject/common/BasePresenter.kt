package com.example.githubproject.common

abstract class BasePresenter<T : BaseView> {
    protected var view: T? = null

    fun bindView(view: T) {
        this.view = view
    }

    fun unbindView() {
        view = null
    }
}