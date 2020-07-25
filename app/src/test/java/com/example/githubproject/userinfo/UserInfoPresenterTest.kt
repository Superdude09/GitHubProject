package com.example.githubproject.userinfo

import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable


class UserInfoPresenterTest {

    @Mock
    private lateinit var view: UserInfoContract.View

    @Mock
    private lateinit var service: ApiService

    private lateinit var presenter: UserInfoPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        // Make sure that trampoline scheduler is used instead of Android's main thread
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }

        presenter = UserInfoPresenter(service)
        presenter.bindView(view)
    }

    @Test
    fun getUser() {
        val userId = "someUserId"

        Mockito.`when`(service.getUser(userId)).thenReturn(Single.just(getMockUserInfoResponse()))
        Mockito.`when`(service.getUserPublicRepos(userId))
            .thenReturn(Single.just(getMockUserReposResponse()))

        presenter.getUser(userId)

        verify(service, times(1)).getUser(userId)
        verify(service, times(1)).getUserPublicRepos(userId)
        verify(view, times(1)).displayUserInfo(getExpectedUserInfo())
        verify(view, times(1)).displayUserRepos(getExpectedUserRepos())
    }

    private fun getMockUserInfoResponse() =
        UserInfoResponse("UserId", "https://www.somewhere.com/img.png")

    private fun getExpectedUserInfo() = UserInfo("UserId", "https://www.somewhere.com/img.png")

    private fun getMockUserReposResponse() = listOf(
        UserRepoInfoResponse(
            "Some repo name",
            "Some repo description",
            "Some Updated At date",
            4,
            6
        )
    )

    private fun getExpectedUserRepos() = listOf(
        UserRepoInfo(
            "Some repo name",
            "Some repo description",
            "Some Updated At date",
            4,
            6
        )
    )
}