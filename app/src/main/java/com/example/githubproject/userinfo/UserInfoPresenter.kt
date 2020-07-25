package com.example.githubproject.userinfo

import com.example.githubproject.common.BasePresenter
import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import com.example.githubproject.userinfo.processor.UserInfoProcessor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(private val service: ApiService, private val userInfoProcessor: UserInfoProcessor) :
    UserInfoContract.Presenter, BasePresenter<UserInfoContract.View>() {

    private var userId: String? = null

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userId: String) {
        Timber.d("Going to retrieve user info")

        this.userId = userId

        compositeDisposable.add(
            service.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::processUserInfoResponse)
                .subscribe(this::onGetUserInfoSuccess, this::onGetUserInfoError)
        )
    }

    private fun getUserRepos(userId: String) {
        Timber.d("Going to retrieve user repo info")

        compositeDisposable.add(
            service.getUserPublicRepos(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::processUserReposResponse)
                .subscribe(this::onGetUserReposSuccess, this::onGetUserReposError)
        )
    }

    override fun onViewDestroyed() {
        compositeDisposable.clear()
    }

    private fun processUserInfoResponse(userInfoDTO: UserInfoResponse) = userInfoProcessor.processUserInfo(userInfoDTO)

    private fun onGetUserInfoSuccess(userInfo: UserInfo) {
        Timber.d("Successfully retrieved user info: $userInfo")

        view?.displayUserInfo(userInfo)

        userId?.let {
            getUserRepos(it)
        }
    }

    private fun onGetUserInfoError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun processUserReposResponse(userRepoInfoDTOs: List<UserRepoInfoResponse>): List<UserRepoInfo> {
        val listUserRepos = mutableListOf<UserRepoInfo>()

        for (userRepoDTO in userRepoInfoDTOs) {
            listUserRepos.add(
                userInfoProcessor.processUserRepoInfo(userRepoDTO)
            )
        }

        return listUserRepos
    }

    private fun onGetUserReposSuccess(listUserRepoInfo: List<UserRepoInfo>) {
        Timber.d("Successfully retrieved user repos: $listUserRepoInfo")

        view?.displayUserRepos(listUserRepoInfo)
    }

    private fun onGetUserReposError(throwable: Throwable) {
        Timber.e(throwable)
    }
}