package com.example.githubproject.userinfo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.userinfo.model.UserRepoInfo
import timber.log.Timber

class UserReposListAdapter(private val listUserRepoInfo: List<UserRepoInfo>) : RecyclerView.Adapter<UserReposListAdapter.UserRepoViewHolder>() {

    private var onUserRepoClickListener: OnUserRepoCLickListener? = null

    @FunctionalInterface
    interface OnUserRepoCLickListener {
        fun onUserRepoClick(userRepoInfo: UserRepoInfo)
    }

    class UserRepoViewHolder(val userRepoItemView: UserRepoComponent) :
        RecyclerView.ViewHolder(userRepoItemView) {

        fun bind(userRepoInfo: UserRepoInfo, listener: OnUserRepoCLickListener) {
            userRepoItemView.setOnClickListener { listener.onUserRepoClick(userRepoInfo) }
        }
    }

    fun setOnUserRepoClickListener(listener: OnUserRepoCLickListener) {
        onUserRepoClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserRepoViewHolder(UserRepoComponent(parent.context))

    override fun getItemCount() = listUserRepoInfo.size

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        try {
            val userRepo = listUserRepoInfo[position]
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