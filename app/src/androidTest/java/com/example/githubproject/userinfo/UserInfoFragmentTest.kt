package com.example.githubproject.userinfo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.githubproject.R
import com.example.githubproject.userinfo.UserInfoFragment.OnUserRepoClickListener
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepoInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserInfoFragmentTest {

    @Test
    fun displayUserInfo() {
        val scenario = launchFragmentInContainer<UserInfoFragment>(themeResId = R.style.AppTheme)
        val name = "User's name"
        val userInfo = UserInfo(name, "https://www.somewhere.com/img.png")

        scenario.onFragment { fragment -> fragment.displayUserInfo(userInfo) }

        onView(withId(R.id.tv_name))
            .check(matches(withText(name)))
    }

    @Test
    fun displayUserRepos() {
        val scenario = launchFragmentInContainer<UserInfoFragment>(themeResId = R.style.AppTheme)
        var numUserRepoInfoEntries = 5

        scenario.onFragment { fragment ->
            fragment.displayUserRepos(
                buildUserRepoInfoList(
                    numUserRepoInfoEntries
                )
            )
        }

        onView(withId(R.id.rv_user_repos)).check(matches(hasChildCount(numUserRepoInfoEntries)))

        // Let's call displayUserRepos() again to simulate another search; want to make sure that
        // number of entries in the adapter is reflected accurately after the second search
        numUserRepoInfoEntries = 3

        scenario.onFragment { fragment ->
            fragment.displayUserRepos(
                buildUserRepoInfoList(
                    numUserRepoInfoEntries
                )
            )
        }

        onView(withId(R.id.rv_user_repos)).check(matches(hasChildCount(numUserRepoInfoEntries)))
    }

    private fun buildUserRepoInfoList(numEntries: Int): List<UserRepoInfo> {
        val listUserRepoInfo = mutableListOf<UserRepoInfo>()

        for (i in 1..numEntries) {
            listUserRepoInfo.add(
                UserRepoInfo(
                    "Repo name $i",
                    "Repo description $i",
                    "2020-06-27T05:52:40Z",
                    i,
                    i
                )
            )
        }

        return listUserRepoInfo
    }

    @Test
    fun displayUserRepos_onClick() {
        val scenario = launchFragmentInContainer<UserInfoFragment>(themeResId = R.style.AppTheme)

        // Build some mock data
        val numUserRepoInfoEntries = 5
        val listUserRepoInfo = buildUserRepoInfoList(numUserRepoInfoEntries)

        // Build the mock callback
        val onClickCallback = mock(OnUserRepoClickListener::class.java)

        // We're going to click at position positionToClick, so prep the mock accordingly
        val positionToClick = 2
        doNothing().`when`(onClickCallback).onUserRepoClicked(listUserRepoInfo[positionToClick])

        scenario.onFragment { fragment ->
            fragment.setOnUserRepoClickedListener(onClickCallback)
            fragment.displayUserRepos(listUserRepoInfo)
        }

        onView(withId(R.id.rv_user_repos)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UserReposListAdapter.UserRepoViewHolder>(
                positionToClick,
                click()
            )
        )

        // Check that the callback was called once for the position that was clicked, and called
        // zero times for all the other positions
        for (i in 0 until numUserRepoInfoEntries) {
            val expectedNumTimesCallbackCalled = if (i == positionToClick) 1 else 0
            verify(onClickCallback, times(expectedNumTimesCallbackCalled)).onUserRepoClicked(
                listUserRepoInfo[i]
            )
        }
    }

    @Test
    fun doSearch() {
        // NOTE: This test case will not work because UserInfoPresenter is a final class and cannot
        // be mocked with Mockito; may need to use Mockk instead
        
//        val scenario = launchFragmentInContainer<UserInfoFragment>(themeResId = R.style.AppTheme)
//
//        val presenter = mock(UserInfoPresenter::class.java)
//        val userId = "Some user id"
//        doNothing().`when`(presenter).getUser(userId)
//
//        scenario.onFragment { fragment ->
//            fragment.presenter = presenter
//            fragment.doSearch(userId)
//        }
//
//        verify(presenter, times(1)).getUser(userId)
    }
}