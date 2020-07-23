package com.example.githubproject.userinfo

import com.example.githubproject.common.BasePresenter
import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserReposResponse
import com.example.githubproject.network.response.UserResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(private val service: ApiService) :
    UserInfoContract.Presenter, BasePresenter<UserInfoFragment>() {

    private var userId: String? = null

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userId: String) {
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

    private fun processUserInfoResponse(userInfoDTO: UserResponse): UserInfo {
        return UserInfo(userInfoDTO.name, userInfoDTO.avatarUrl)
    }

    private fun onGetUserInfoSuccess(userInfo: UserInfo) {
        Timber.d("Successfully retrieved user info: ${userInfo.toString()}")

        view?.displayUserInfo(userInfo)

        userId?.let {
            getUserRepos(it)
        }
    }

    private fun onGetUserInfoError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun processUserReposResponse(userReposDTOs: List<UserReposResponse>): List<UserRepo> {
        val listUserRepos = mutableListOf<UserRepo>()

        for (userRepoDTO in userReposDTOs) {
            listUserRepos.add(
                UserRepo(
                    userRepoDTO.name,
                    userRepoDTO.description,
                    userRepoDTO.updatedAt,
                    userRepoDTO.stargazersCount,
                    userRepoDTO.forks
                )
            )
        }

        return listUserRepos
    }

    private fun onGetUserReposSuccess(userRepos: List<UserRepo>) {
        Timber.d("Successfully retrieved user repos: ${userRepos.toString()}")

        view?.displayUserRepos(userRepos)
    }

    private fun onGetUserReposError(throwable: Throwable) {
        Timber.e(throwable)
    }
}