package com.example.githubproject.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubproject.GitHubProjectApplication
import com.example.githubproject.R
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_user_info.*
import javax.inject.Inject

class UserInfoFragment : Fragment(), UserInfoContract.View  {

    @Inject
    lateinit var presenter: UserInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as GitHubProjectApplication).getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.layout_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.bindView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.unbindView()
    }

    override fun displayUserInfo(userInfo: UserInfo) {
        tv_user_id.text = userInfo.userId
        Picasso.get().load(userInfo.avatarUrl).into(user_avatar)
    }

    override fun displayUserRepos(userRepos: List<UserRepo>) {
//        TODO("Not yet implemented")
    }

    fun doSearch(userId: String) {
        presenter.getUser(userId)
    }
}