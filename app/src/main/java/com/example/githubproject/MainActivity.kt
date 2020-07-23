package com.example.githubproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.githubproject.search.SearchContract
import com.example.githubproject.search.SearchInputFragment
import com.example.githubproject.search.SearchPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchInputFragment.OnSearchSubmittedListener{
//    @Inject
//    protected lateinit var presenter: SearchPresenter

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
        Toast.makeText(this, searchValue, Toast.LENGTH_SHORT).show();
    }

}
