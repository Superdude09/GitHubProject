package com.example.githubproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubproject.search.SearchContract
import com.example.githubproject.search.SearchPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchContract.View {
    @Inject
    protected lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationComponent = GitHubProjectApplication.get(this).getAppComponent()

    }

    override fun onStart() {
        super.onStart()
    }
}
