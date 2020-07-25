package com.example.githubproject.search

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubproject.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchInputFragmentTest {
    @Test
    fun testEventFragment() {
        launchFragmentInContainer<SearchInputFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.searchButton)).check(matches(withText("Search")))
        onView(withId(R.id.search_input_edit_text)).check(matches(withHint("Enter a github user id")))
    }
}