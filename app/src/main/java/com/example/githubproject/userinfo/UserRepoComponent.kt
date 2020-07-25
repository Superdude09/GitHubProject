package com.example.githubproject.userinfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.githubproject.R
import kotlinx.android.synthetic.main.layout_user_repo_list_item.view.*

class UserRepoComponent @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {
   init {
       inflate()
       initialize()
   }

    private fun inflate() {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_user_repo_list_item, this, true)
    }

    private fun initialize() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val marginInDP = resources.getDimensionPixelSize(R.dimen.dimen_8_dp)
        (layoutParams as MarginLayoutParams).setMargins(0, 0, 0, marginInDP)

        val paddingInDP = resources.getDimensionPixelSize(R.dimen.dimen_8_dp)
        setContentPadding(paddingInDP, paddingInDP, paddingInDP, paddingInDP)

        elevation = resources.getDimensionPixelSize(R.dimen.dimen_8_dp).toFloat()

        radius = resources.getDimensionPixelSize(R.dimen.corner_radius).toFloat()

        useCompatPadding = true
    }

    fun setName(name: String) {
        tv_user_repo_name.text = name
    }

    fun setDescription(description: String) {
        tv_user_repo_description.text = description
    }
}