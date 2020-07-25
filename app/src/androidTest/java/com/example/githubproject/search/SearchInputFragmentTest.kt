package com.example.githubproject.search

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubproject.R
import com.example.githubproject.search.SearchInputFragment.OnSearchSubmittedListener
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class SearchInputFragmentTest {
    @Test
    fun searchInputFragment_views() {
        launchFragmentInContainer<SearchInputFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.search_button)).check(matches(withText("Search")))
        onView(withId(R.id.search_input_edit_text)).check(matches(withHint("Enter a github user id")))
    }

    @Test
    fun searchInputFragment_searchButtonOnClick() {
        val scenario = launchFragmentInContainer<SearchInputFragment>(themeResId = R.style.AppTheme)
        val userId = "foobar"

        val onClickCallback = mock(OnSearchSubmittedListener::class.java)
        doNothing().`when`(onClickCallback).onSearchSubmitted(userId)

        scenario.onFragment { fragment -> fragment.setOnSearchSubmittedListener(onClickCallback) }

        onView(withId(R.id.search_input_edit_text)).perform(typeText(userId))
        onView(withId(R.id.search_button)).perform(click())

        verify(onClickCallback, times(1)).onSearchSubmitted(userId)
    }
}