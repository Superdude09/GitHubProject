package com.example.githubproject.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubproject.GitHubProjectApplication
import com.example.githubproject.R
import com.example.githubproject.userinfo.model.UserInfo
import com.example.githubproject.userinfo.model.UserRepo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_user_info.*
import timber.log.Timber
import javax.inject.Inject

class UserInfoFragment : Fragment(), UserInfoContract.View  {

    @Inject
    lateinit var presenter: UserInfoPresenter

    private var userInfoListAdapter: UserReposListAdapter? = null
    private val listUserRepos = mutableListOf<UserRepo>()

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
        initializeUserReposRecyclerView()
        presenter.bindView(this)
    }

    private fun initializeUserReposRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        userInfoListAdapter = UserReposListAdapter(listUserRepos)
        userInfoListAdapter?.setOnUserRepoClickListener(object: UserReposListAdapter.OnUserRepoCLickListener {
            override fun onUserRepoClick(userRepo: UserRepo) {
//                TODO("Not yet implemented")
                Timber.d("Clicked on user repo with name ${userRepo.name}")
            }
        })

        rv_user_repos.apply {
            setHasFixedSize(true)
            setLayoutManager(layoutManager)
            adapter = userInfoListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
    }

    override fun displayUserInfo(userInfo: UserInfo) {
        tv_user_id.text = userInfo.userId
        Picasso.get().load(userInfo.avatarUrl).into(user_avatar)

        doFadeInAnimation(tv_user_id)
        doFadeInAnimation(user_avatar)
    }

    override fun displayUserRepos(userRepos: List<UserRepo>) {
        listUserRepos.clear()
        listUserRepos.addAll(userRepos)
        userInfoListAdapter?.notifyDataSetChanged()

        doFadeInAnimation(rv_user_repos)
    }

    private fun doFadeInAnimation(viewToAnimate: View) {
        AnimationUtils.loadAnimation(context, R.anim.view_fade_in_anim).also {
                animation -> viewToAnimate.startAnimation(animation)
        }
    }

    fun doSearch(userId: String) {
        presenter.getUser(userId)
    }
}