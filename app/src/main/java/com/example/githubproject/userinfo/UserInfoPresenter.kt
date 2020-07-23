package com.example.githubproject.userinfo

import com.example.githubproject.network.ApiService
import com.example.githubproject.userinfo.model.UserResponse
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(private val service: ApiService) :
    UserInfoContract.Presenter {

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
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable)
        throwable.printStackTrace()
    }
}