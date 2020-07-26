package com.example.githubproject.userinfo

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.githubproject.R
import com.example.githubproject.common.util.DateUtil
import com.example.githubproject.userinfo.model.UserRepoInfo
import org.junit.Test

class UserRepoBottomSheetDialogFragmentTest {
    @Test
    fun dialogFragmentViews() {
        val name = "Some repo"
        val description = "Some repo description"
        val rawUpdatedAt = "2020-06-27T05:52:40Z"
        val formattedUpdatedAt = DateUtil.convertDateStringFormat(rawUpdatedAt,
            UserRepoBottomSheetDialogFragment.DATE_FORMAT_FROM_RESPONSE,
            UserRepoBottomSheetDialogFragment.DATE_FORMAT_TO_DISPLAY
        )
        val stargazersCount = 111
        val forks = 222

        val fragmentArgs = Bundle().apply {
            putParcelable("PARAM_USER_REPO", UserRepoInfo(name, description, rawUpdatedAt, stargazersCount, forks))
        }

        val scenario = launchFragmentInContainer<UserRepoBottomSheetDialogFragment>(fragmentArgs = fragmentArgs)

        onView(withId(R.id.tv_last_updated_label)).check(matches(withText("Last Updated")))
        onView(withId(R.id.tv_last_updated_value)).check(matches(withText(formattedUpdatedAt)))

        onView(withId(R.id.tv_stars_label)).check(matches(withText("Stars")))
        onView(withId(R.id.tv_stars_value)).check(matches(withText(stargazersCount.toString())))

        onView(withId(R.id.tv_forks_label)).check(matches(withText("Forks")))
        onView(withId(R.id.tv_forks_value)).check(matches(withText(forks.toString())))
    }
}