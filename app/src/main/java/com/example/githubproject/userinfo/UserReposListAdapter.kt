package com.example.githubproject.userinfo

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.userinfo.model.UserRepo
import timber.log.Timber
import java.lang.IndexOutOfBoundsException

class UserReposListAdapter(private val listUserRepos: List<UserRepo>) : RecyclerView.Adapter<UserReposListAdapter.UserRepoViewHolder>() {

    private var onUserRepoClickListener: OnUserRepoCLickListener? = null

    interface OnUserRepoCLickListener {
        fun onUserRepoClick(userRepo: UserRepo)
    }

    class UserRepoViewHolder(val userRepoItemView: TextView) :
        RecyclerView.ViewHolder(userRepoItemView) {

        fun bind(userRepo: UserRepo, listener: OnUserRepoCLickListener) {
            userRepoItemView.setOnClickListener { listener.onUserRepoClick(userRepo) }
        }
    }

    fun setOnUserRepoClickListener(listener: OnUserRepoCLickListener) {
        onUserRepoClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserRepoViewHolder(TextView(parent.context))

    override fun getItemCount() = listUserRepos.size

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        try {
            val userRepo = listUserRepos[position]
            holder.userRepoItemView.text = userRepo.name    // TODO: Flesh this part out with custom component
            onUserRepoClickListener?.let { holder.bind(userRepo, it) }
        } catch (ex: IndexOutOfBoundsException) {
            Timber.e(ex)
        }
    }
}