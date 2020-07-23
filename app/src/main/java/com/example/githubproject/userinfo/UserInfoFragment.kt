package com.example.githubproject.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubproject.GitHubProjectApplication
import com.example.githubproject.R
import kotlinx.android.synthetic.main.layout_user_info.*
import javax.inject.Inject

class UserInfoFragment : Fragment()  {

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

    fun setUserid(userId: String) {
        tv_user_id.text = userId

        presenter.getUser(userId)
    }
}