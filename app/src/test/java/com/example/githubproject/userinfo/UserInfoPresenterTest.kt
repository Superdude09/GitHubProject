package com.example.githubproject.userinfo

import com.example.githubproject.network.ApiService
import com.example.githubproject.network.response.UserRepoInfoResponse
import com.example.githubproject.network.response.UserInfoResponse
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import com.example.githubproject.userinfo.processor.UserInfoProcessorImpl
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.lang.RuntimeException
import java.util.concurrent.Callable


class UserInfoPresenterTest {

    @Mock
    private lateinit var view: UserInfoContract.View

    @Mock
    private lateinit var service: ApiService

    private val userInfoProcessor = UserInfoProcessorImpl()

    private lateinit var presenter: UserInfoPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        // Make sure that trampoline scheduler is used instead of Android's main thread
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = UserInfoPresenter(service, userInfoProcessor)
        presenter.bindView(view)
    }

    @Test
    fun getUser() {
        val userId = "someUserId"

        `when`(service.getUser(userId)).thenReturn(Single.just(getMockUserInfoResponse()))
        `when`(service.getUserPublicRepos(userId))
            .thenReturn(Single.just(getMockUserReposResponse()))

        presenter.getUser(userId)

        verify(service, times(1)).getUser(userId)
        verify(service, times(1)).getUserPublicRepos(userId)
        verify(view, times(1)).displayUserInfo(getExpectedUserInfo())
        verify(view, times(1)).displayUserRepos(getExpectedUserRepos())
    }

    @Test
    fun getUser_getUserFails() {
        val userId = "someUserId"

        `when`(service.getUser(userId)).thenReturn(Single.error(RuntimeException()))

        presenter.getUser(userId)

        verify(service, times(1)).getUser(userId)
        verify(view, times(1)).handleUserInfoError()
    }

    @Test
    fun getUser_getUserPublicRepoFails() {
        val userId = "someUserId"

        `when`(service.getUser(userId)).thenReturn(Single.just(getMockUserInfoResponse()))
        `when`(service.getUserPublicRepos(userId))
            .thenReturn(Single.error(RuntimeException()))

        presenter.getUser(userId)

        verify(service, times(1)).getUser(userId)
        verify(service, times(1)).getUserPublicRepos(userId)
        verify(view, times(1)).displayUserInfo(getExpectedUserInfo())
        verify(view, times(1)).handleUserRepoInfoError()
    }

    private fun getMockUserInfoResponse() =
        UserInfoResponse("UserId", "https://www.somewhere.com/img.png")

    private fun getExpectedUserInfo() = userInfoProcessor.processUserInfo(getMockUserInfoResponse())

    private fun getMockUserReposResponse() = listOf(
        UserRepoInfoResponse(
            "Some repo name",
            "Some repo description",
            "Some Updated At date",
            4,
            6
        )
    )

    private fun getExpectedUserRepos(): List<UserRepoInfo> {
        val listUserRepoInfo = mutableListOf<UserRepoInfo>()

        for (userRepoInfoResponse in getMockUserReposResponse()) {
            listUserRepoInfo.add(userInfoProcessor.processUserRepoInfo(userRepoInfoResponse))
        }

        return listUserRepoInfo
    }
}