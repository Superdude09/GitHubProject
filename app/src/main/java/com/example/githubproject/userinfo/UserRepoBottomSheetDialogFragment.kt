package com.example.githubproject.userinfo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import com.example.githubproject.R
import com.example.githubproject.common.util.DateUtil
import com.example.githubproject.userinfo.model.UserRepoInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_user_info_bottom_sheet.*

class UserRepoBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val PARAM_USER_REPO = "PARAM_USER_REPO"

        const val DATE_FORMAT_FROM_RESPONSE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val DATE_FORMAT_TO_DISPLAY = "MMM dd, yyyy HH:mm:ss a"

        fun getInstance(userRepoInfo: UserRepoInfo) : UserRepoBottomSheetDialogFragment {
            val fragment = UserRepoBottomSheetDialogFragment()

            val args = Bundle()
            args.putParcelable(PARAM_USER_REPO, userRepoInfo)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = layoutInflater.inflate(R.layout.layout_user_info_bottom_sheet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setBackgroundColor(Color.TRANSPARENT)
        adjustMargins()
    }

    private fun setBackgroundColor(@ColorInt colorId: Int) {
        (view?.parent as View).setBackgroundColor(colorId)
    }

    private fun adjustMargins() {
        val marginsInDP = resources.getDimensionPixelSize(R.dimen.dimen_8_dp)

        val layoutParams = view?.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(marginsInDP, marginsInDP, marginsInDP, marginsInDP)

        view?.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepoInfo: UserRepoInfo? = arguments?.getParcelable(PARAM_USER_REPO)

        userRepoInfo?.let {
            val rawUpdateAt = it.updatedAt
            val formattedUpdateAt = if (rawUpdateAt != null) DateUtil.convertDateStringFormat(rawUpdateAt, DATE_FORMAT_FROM_RESPONSE, DATE_FORMAT_TO_DISPLAY) else ""

            val stars = it.stargazersCount
            val forks = it.forks

            tv_last_updated_value.text = formattedUpdateAt
            tv_stars_value.text = stars.toString()
            tv_forks_value.text = forks.toString()
        }
    }
}