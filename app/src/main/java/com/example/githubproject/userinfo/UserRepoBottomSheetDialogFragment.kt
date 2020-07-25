package com.example.githubproject.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubproject.R
import com.example.githubproject.userinfo.model.UserRepo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
}