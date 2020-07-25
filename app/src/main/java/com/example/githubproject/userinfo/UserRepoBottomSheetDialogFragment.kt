package com.example.githubproject.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubproject.R
import com.example.githubproject.userinfo.model.UserRepo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_user_info_bottom_sheet.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class UserRepoBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private val PARAM_USER_REPO = "PARAM_USER_REPO"

        fun getInstance(userRepo: UserRepo) : UserRepoBottomSheetDialogFragment {
            val fragment = UserRepoBottomSheetDialogFragment()

            val args = Bundle()
            args.putParcelable(PARAM_USER_REPO, userRepo)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = layoutInflater.inflate(R.layout.layout_user_info_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepoInfo: UserRepo? = arguments?.getParcelable(PARAM_USER_REPO)

        userRepoInfo?.let {
            val rawUpdateAt = it.updatedAt

            // 2020-07-23T02:16:26Z
            // 2001-07-04T12:08:56.235-0700
            // Jul 6, 2017 12:15:11 AM
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val dateUpdateAt = dateFormat.parse(rawUpdateAt)
            dateFormat.applyPattern("MMM dd, yyyy HH:mm:ss a")
            val formattedUpdateAt = dateFormat.format(dateUpdateAt)
            val stars = it.stargazersCount
            val forks = it.forks

            tv_last_updated_value.text = formattedUpdateAt
            tv_stars_value.text = stars.toString()
            tv_forks_value.text = forks.toString()
        }
    }
}