package com.example.githubproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.githubproject.search.SearchInputFragment
import com.example.githubproject.userinfo.UserInfoFragment

class MainActivity : AppCompatActivity(), SearchInputFragment.OnSearchSubmittedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationComponent = GitHubProjectApplication.get(this).getAppComponent()

    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is SearchInputFragment) {
            fragment.setOnHeadlineSelectedListener(this)
        }
    }

    override fun onSearchSubmitted(searchValue: String) {
        val userInfoFragment = supportFragmentManager.findFragmentById(R.id.user_info_fragment) as UserInfoFragment

        userInfoFragment?.let {
            userInfoFragment.doSearch(searchValue)
        }
    }
}
