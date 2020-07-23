package com.example.githubproject.userinfo

import com.example.githubproject.common.BasePresenter
import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserResponse
import com.example.githubproject.userinfo.model.UserInfo
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(private val service: ApiService) :
    UserInfoContract.Presenter, BasePresenter<UserInfoFragment>() {

    private var userInfo: UserInfo? = null

    init {
        Timber.e("KEVIN!")
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userId: String) {
        compositeDisposable.add(
            service.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    override fun onViewDestroyed() {
        compositeDisposable.clear()
    }

    private fun onSuccess(response: UserResponse) {
        Timber.d(response.toString())

        userInfo = UserInfo(response.name, response.avatarUrl)

        userInfo?.let {
            view?.displayUserInfo(it)
        }
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable)
        throwable.printStackTrace()
    }

}