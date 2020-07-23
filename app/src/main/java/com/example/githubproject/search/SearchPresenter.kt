package com.example.githubproject.search

import com.example.githubproject.network.ApiService
import com.example.githubproject.search.model.UserResponse
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val service: ApiService) :
    SearchContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userId: String) {
        compositeDisposable.add(
            service.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess)
        )
    }

    override fun onViewDestroyed() {
        compositeDisposable.clear()
    }

    private fun onSuccess(response: UserResponse) {
        Timber.d(response.toString())
    }
}