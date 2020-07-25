package com.example.githubproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubproject.search.SearchInputFragment
import com.example.githubproject.userinfo.UserInfoFragment
import com.example.githubproject.userinfo.UserRepoBottomSheetDialogFragment
import com.example.githubproject.userinfo.model.UserRepoInfo

class MainActivity : AppCompatActivity(),
    SearchInputFragment.OnSearchSubmittedListener,
    UserInfoFragment.OnUserRepoClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationComponent = GitHubProjectApplication.get(this).getAppComponent()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is SearchInputFragment) {
            fragment.setOnSearchSubmittedListener(this)
        } else if (fragment is UserInfoFragment) {
            fragment.setOnUserRepoClickedListener(this)
        }
    }

    override fun onSearchSubmitted(searchValue: String) {
        val userInfoFragment =
            supportFragmentManager.findFragmentById(R.id.user_info_fragment) as UserInfoFragment

        userInfoFragment.let {
            userInfoFragment.doSearch(searchValue)
        }
    }

    override fun onUserRepoClicked(userRepoInfo: UserRepoInfo) {
        val bottomSheetDialogFragment = UserRepoBottomSheetDialogFragment.getInstance(userRepoInfo)
        bottomSheetDialogFragment.show(
            supportFragmentManager,
            UserRepoBottomSheetDialogFragment::class.java.canonicalName
        )
    }
}
