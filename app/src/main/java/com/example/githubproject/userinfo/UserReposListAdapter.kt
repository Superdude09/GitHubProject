package com.example.githubproject.userinfo

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.userinfo.model.UserRepo
import timber.log.Timber
import java.lang.IndexOutOfBoundsException

class UserReposListAdapter(private val listUserRepos: List<UserRepo>) : RecyclerView.Adapter<UserReposListAdapter.UserRepoViewHolder>() {

    private var onUserRepoClickListener: OnUserRepoCLickListener? = null

    @FunctionalInterface
    interface OnUserRepoCLickListener {
        fun onUserRepoClick(userRepo: UserRepo)
    }

    class UserRepoViewHolder(val userRepoItemView: UserRepoComponent) :
        RecyclerView.ViewHolder(userRepoItemView) {

        fun bind(userRepo: UserRepo, listener: OnUserRepoCLickListener) {
            userRepoItemView.setOnClickListener { listener.onUserRepoClick(userRepo) }
        }
    }

    fun setOnUserRepoClickListener(listener: OnUserRepoCLickListener) {
        onUserRepoClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserRepoViewHolder(UserRepoComponent(parent.context))

    override fun getItemCount() = listUserRepos.size

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        try {
            val userRepo = listUserRepos[position]
            userRepo.name?.let {
                holder.userRepoItemView.setName(it)
            }

            userRepo.description?.let {
                holder.userRepoItemView.setDescription(it)
            }

            onUserRepoClickListener?.let {
                holder.bind(userRepo, it)
            }
        } catch (ex: IndexOutOfBoundsException) {
            Timber.e(ex)
        }
    }
}