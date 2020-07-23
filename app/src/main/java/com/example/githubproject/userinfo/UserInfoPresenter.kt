package com.example.githubproject.userinfo

import com.example.githubproject.common.BasePresenter
import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserReposResponse
import com.example.githubproject.network.response.UserResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(private val service: ApiService) :
    UserInfoContract.Presenter, BasePresenter<UserInfoFragment>() {

    private var userId: String? = null
    private var userInfo: UserInfo? = null
    private var userRepos: List<UserRepo>? = mutableListOf()

    init {
        Timber.e("KEVIN!")
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userId: String) {
        this.userId = userId

        compositeDisposable.add(
            service.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetUserInfoSuccess, this::onGetUserInfoError)
        )
    }

    private fun getUserRepos(userId: String) {
        compositeDisposable.add(
            service.getUserPublicRepos(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetUserReposSuccess, this::onGetUserReposError)
        )
    }

    override fun onViewDestroyed() {
        compositeDisposable.clear()
    }

    private fun onGetUserInfoSuccess(response: UserResponse) {
        Timber.d(response.toString())

        userInfo = UserInfo(response.name, response.avatarUrl)

        userInfo?.let {
            view?.displayUserInfo(it)
        }

        userId?.let {
            getUserRepos(it)
        }
    }

    private fun onGetUserInfoError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun onGetUserReposSuccess(response: List<UserReposResponse>) {
        Timber.d(response.toString())

//        userRepos = User

//        userInfo?.let {
//            view?.displayUserInfo(it)
//        }
    }

    private fun onGetUserReposError(throwable: Throwable) {
        Timber.e(throwable)
        throwable.printStackTrace()
    }
}